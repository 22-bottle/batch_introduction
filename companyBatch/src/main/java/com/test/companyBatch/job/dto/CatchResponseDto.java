package com.test.companyBatch.job.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "Data")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatchResponseDto {

    @XmlElement(name = "ResultMsg")
    private String resultMsg;
    @XmlElement(name = "PageSize")
    private int pageSize;
    @XmlElement(name = "RowCount")
    private int rowCount;
    @XmlElement(name = "Companys")
    private CompanysResponseDto companys;

}
