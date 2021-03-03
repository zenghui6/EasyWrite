package com.zenghui.easywrite.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 * @author HUT-zenghui
 */
@Data
public class AdminSearchDto {
    private int page;
    private int size;
    private String keywords;
    private Sort.Direction direction;
    private String active;
    private String level;
}