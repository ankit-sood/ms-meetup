package com.ms.meetup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ms.meetup.constants.MeetupConstants;
import com.ms.meetup.model.EventRequest;
import com.ms.meetup.model.UserDetails;
import com.ms.meetup.repository.EventRequestRepository;
import com.ms.meetup.vo.EventRequestVO;

@Service
public class EventRequestService {
	@Autowired
	private EventRequestRepository eventRequestRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public String joinEvent(EventRequestVO eventRequestVO, Long eventId) {
		EventRequest eventRequest = getEventRequestVOFromModel(eventRequestVO, eventId);
		eventRequestRepository.save(eventRequest);
		return "SUCCESS";
	}
	
	public String acceptEventRequests(Long eventId,Long userId,Integer statusId) throws Exception {
		List<EventRequest> eventRequestList = new ArrayList<>();
		if(userId==null) {
			eventRequestList = eventRequestRepository.findByEventId(eventId);
		}else {
			eventRequestList = eventRequestRepository.findByEventIdAndUserId(eventId, userId);
		}
		if(!CollectionUtils.isEmpty(eventRequestList)) {
			for(EventRequest eventRequest:eventRequestList) {
				if(statusId==1) {
					eventRequest.setStatus(MeetupConstants.STATUS_ACCEPTED);
				}else if(statusId==2) {
					eventRequest.setStatus(MeetupConstants.STATUS_REJECTED);
				}
			}
			eventRequestRepository.saveAll(eventRequestList);
			return "SUCCESS";
		}else {
			throw new Exception("Unable to update the status with given data.");
		}
		
	}
	
	//requests for each event
	public List<EventRequestVO> getRequestForEvent(Long eventId) throws Exception {
		List<EventRequestVO> eventRequestVOList = new ArrayList<>();
		List<EventRequest> eventRequestsList = eventRequestRepository.findByEventId(eventId);
		if(!CollectionUtils.isEmpty(eventRequestsList)) {
			List<Long> userIdList = eventRequestsList.stream().map(t -> t.getUserId()).collect(Collectors.toList());
			Map<Long,UserDetails> userDetailsMap = userDetailsService.getUserDetails(userIdList);
			if(userDetailsMap!=null) {
				for(EventRequest eventRequest:eventRequestsList) {
					EventRequestVO eventRequestVO = getEventRequestVOFromModel(eventRequest);
					UserDetails userDetails = userDetailsMap.get(eventRequest.getUserId());
					if(userDetails!=null) {
						eventRequestVO.setUserName(userDetails.getUsername());
						eventRequestVO.setEmailId(userDetails.getEmailId());
						eventRequestVO.setEventId(eventId);
					}
					eventRequestVOList.add(eventRequestVO);
				}
			}
		}
		return eventRequestVOList;
	}
	
	public EventRequestVO getEventRequestVOFromModel(EventRequest eventRequest) {
		EventRequestVO eventRequestVO  = new EventRequestVO();
		eventRequestVO.setRequestId(eventRequest.getRequestId());
		eventRequestVO.setStatus(eventRequest.getStatus());
		eventRequestVO.setUserId(eventRequest.getUserId());
		return eventRequestVO;
	}
	
	public EventRequest getEventRequestVOFromModel(EventRequestVO eventRequestVO,Long eventId) {
		EventRequest eventRequest  = new EventRequest();
		eventRequest.setStatus(MeetupConstants.STATUS_REQUESTED);
		eventRequest.setUserId(eventRequestVO.getUserId());
		eventRequest.setEventId(eventId);
		return eventRequest;
	}
	
	
}
