package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryVO {

//	CREATE TABLE PRODUCT_CATEGORY(
//			CATEGORY_ID INT PRIMARY KEY AUTO_INCREMENT,
//		    GROUP_ID VARCHAR(10),
//		    CATEGORY_LV INT, ##1,2,3
//		    CATEGORY_NM VARCHAR(100), ##대분류중분류소분류
//		    CATEGORY_DETAIL_LV INT, ##ORDER순서
//		    CATEGORY_DETAIL_NM VARCHAR(100), ##이름
//		    CATEGORY_PARENT_LV INT , ##1,2,3에 대한 부모컬럼
//		    CATEGORY_DETAIL_PARENT_LV INT ##ORDER순서에 대한 부모컬럼
//	);
	
	private Integer category_id;
	private String group_id;
	private Integer category_lv;
	private String category_nm;
	private Integer category_detail_lv;
	private String category_detail_nm;
	private Integer category_parent_lv;
	private Integer category_detail_parent_lv;	
	
}
