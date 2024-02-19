package com.test.companyBatch.job.entity;

import com.test.companyBatch.job.dto.CompanyResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "company_code")
    private Long companyCode;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_image")
    private String companyImage;
    @Column(name = "job_code_name")
    private String jobCodeName;
    @Column(name = "company_size_name")
    private String companySizeName;
    @Column(name = "ipo_name")
    private String ipoName;
    @Column(name = "ceo_name")
    private String ceoName;
    @Column(name = "business_field")
    private String businessField;
    @Column(name = "found_date")
    private String foundDate;
    @Column(name = "company_homepage")
    private String companyHomepage;
    @Column(name = "company_address")
    private String companyAddress;
    @Column(name = "domain")
    private String domain;

    public static Company toEntity(CompanyResponseDto dto) {
        return Company.builder()
                .companyCode(dto.getCompRegNum())
                .companyName(dto.getCompName())
                .companyImage(dto.getCi())
                .jobCodeName(dto.getJinhakCodeName())
                .companySizeName(dto.getCompSizeName())
                .ipoName(dto.getIpoName())
                .ceoName(dto.getCeoName())
                .businessField(dto.getBusinessField())
                .foundDate(dto.getFoundDate())
                .companyHomepage(dto.getCompHomepage())
                .companyAddress(dto.getCompAddress())
                .build();
    }

}
