package com.ms.meetup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ms.meetup.model.MessageThread;
import com.ms.meetup.model.UserDetails;
import com.ms.meetup.repository.MessageThreadRepository;
import com.ms.meetup.vo.MessageThreadRequestVO;
import com.ms.meetup.vo.MessageThreadResponseVO;

@Service
public class MessageThreadService {
	
	@Autowired
	private MessageThreadRepository messageThreadRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public void addMessage(MessageThreadRequestVO messageThreadRequestVO) {
		MessageThread messageThread = getMessageThreadfromVO(messageThreadRequestVO);
		messageThreadRepository.save(messageThread);
	}
	
	public List<MessageThreadResponseVO> getMessageInfo(Long eventId) throws Exception {
		List<MessageThreadResponseVO> messageThreadResponseVOList = new ArrayList<>();
		List<MessageThread> messageThreadList = messageThreadRepository.findByEventId(eventId);
		if(!CollectionUtils.isEmpty(messageThreadList)) {
			List<Long> userIdList = messageThreadList.stream().map(t -> t.getUserId()).collect(Collectors.toList());
			Map<Long,UserDetails> userDetailsMap = userDetailsService.getUserDetails(userIdList);
			if(userDetailsMap!=null) {
				for(MessageThread messageThread:messageThreadList) {
					MessageThreadResponseVO messageThreadResponseVO = getMessageThreadResponseVOFromModel(messageThread);
					UserDetails userDetails = userDetailsMap.get(messageThread.getUserId());
					if(userDetails!=null) {
						messageThreadResponseVO.setUserName(userDetails.getUsername());
					}
					messageThreadResponseVOList.add(messageThreadResponseVO);
				}
			}
		}
		return messageThreadResponseVOList;
	}
	
	private MessageThread getMessageThreadfromVO(MessageThreadRequestVO messageThreadRequestVO) {
		MessageThread messageThread = new MessageThread();
		messageThread.setEventId(messageThreadRequestVO.getEventId());
		messageThread.setMessage(messageThreadRequestVO.getMessage());
		messageThread.setPostedAt(messageThreadRequestVO.getPostedAt());
		messageThread.setUserId(messageThreadRequestVO.getUserId());
		return messageThread;
	}
	
	private MessageThreadResponseVO getMessageThreadResponseVOFromModel(MessageThread messageThread) {
		MessageThreadResponseVO messageThreadResponseVO = new MessageThreadResponseVO();
		
		return messageThreadResponseVO;
	}
}
