package com.zenghui.easywrite.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

/**
 * @author Hocassian
 */
@Data
public class SearchDto {
    private int page;
    private int size;
    private String keywords;
    private Sort.Direction direction;
}