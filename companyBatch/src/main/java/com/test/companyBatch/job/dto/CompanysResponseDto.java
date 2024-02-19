package com.test.companyBatch.job.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "Companys")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanysResponseDto {

    @XmlElement(name = "Company")
    private List<CompanyResponseDto> companyList;

}
