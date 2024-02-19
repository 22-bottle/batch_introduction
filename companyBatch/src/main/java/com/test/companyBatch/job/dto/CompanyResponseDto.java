package com.test.companyBatch.job.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyResponseDto {

    @XmlElement(name = "CompID")
    private int compID;
    @XmlElement(name = "CompName")
    private String compName;
    @XmlElement(name = "CI")
    private String ci;
    @XmlElement(name = "JinhakCodeName")
    private String jinhakCodeName;
    @XmlElement(name = "CompSizeName")
    private String compSizeName;
    @XmlElement(name = "IPOName")
    private String ipoName;
    @XmlElement(name = "TotJum")
    private float totJum;
    @XmlElement(name = "ReviewScore")
    private float reviewScore;
    @XmlElement(name = "CEOName")
    private String ceoName;
    @XmlElement(name = "CompRegNum")
    private Long compRegNum;
    @XmlElement(name = "BusinessField")
    private String businessField;
    @XmlElement(name = "FoundDate")
    private String foundDate;
    @XmlElement(name = "FinancialPeriod")
    private String financialPeriod;
    @XmlElement(name = "CompSalesCon")
    private String compSalesCon;
    @XmlElement(name = "CompSales")
    private String compSales;
    @XmlElement(name = "CompCapitalCon")
    private String compCapitalCon;
    @XmlElement(name = "CompCapital")
    private String compCapital;
    @XmlElement(name = "CompNetProfitCon")
    private String compNetProfitCon;
    @XmlElement(name = "CompNetProfit")
    private String compNetProfit;
    @XmlElement(name = "CompHomepage")
    private String compHomepage;
    @XmlElement(name = "CompAddress")
    private String compAddress;

}
