//package com.netease.engine.service;
//
//import java.io.File;
//import java.util.Date;
//import java.util.List;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.alibaba.fastjson.JSONObject;
//import com.netease.engine.constant.Constant;
//import com.netease.engine.mapper.CommentInfoMapper;
//import com.netease.engine.mapper.IndexInfoMapper;
//import com.netease.engine.model.CommentInfo;
//import com.netease.engine.util.DateUtil;
//
//@Service("pushCommentDataService")
//public class PushCommentDataService {
//	
//	private final Log logger = LogFactory.getLog(this.getClass());
//	
//	@Autowired
//	private CommentInfoMapper commentInfoMapper;
//	@Autowired
//	private IndexInfoMapper indexInfoMapper;
//	
//	public void push() {
//		List<CommentInfo> list = null;
//		try{
//		    Long index = indexInfoMapper.selectIndex(Constant.WEIXIN_COMMENT_KEY);
//			if(index == null){
//				index = 0l;
//			}
//			list = commentInfoMapper.selectPushData(index,Constant.WEIXIN_COMMENT_STEP,1);
//			StringBuilder sb = new StringBuilder();			
//			if(!list.isEmpty()){
//				Long stat = list.get(0).getId();
//				index = list.get(list.size()-1).getId();
//				indexInfoMapper.updateIndex(Constant.WEIXIN_COMMENT_KEY, index);
//				commentInfoMapper.updateCommentStatusById(stat,index);
//				for(CommentInfo info : list){
//					sb.append(JSONObject.toJSON(info)+"\n");
//				}
//				//写文件
//				FileUtils.writeStringToFile(new File(Constant.WEIXIN_JSON_PATH
//						+ DateUtil.formatDateForJson(new Date()) +".comment.json"),sb.toString(), Constant.DEFAULT_ENCODING);
//			}else{
//				//写文件
//				FileUtils.writeStringToFile(new File(Constant.WEIXIN_JSON_PATH
//						+ DateUtil.formatDateForJson(new Date()) +".comment.json"),"", Constant.DEFAULT_ENCODING);
//			}
//			
//		}catch(Exception e){
//			String msg = "PushCommentDataService推送信息保存失败"+JSONObject.toJSON(list);
//			e.printStackTrace();
//			logger.error(msg,e);
//			throw new RuntimeException(msg,e);
//		}
//	}
//	
//}
