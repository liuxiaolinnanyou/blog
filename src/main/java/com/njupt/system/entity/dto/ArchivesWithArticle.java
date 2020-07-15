package com.njupt.system.entity.dto;

import com.njupt.system.entity.SysArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用于封装Article表按时间归档的DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivesWithArticle implements Serializable {

    private String date;
    private List<SysArticle> articles;
}
