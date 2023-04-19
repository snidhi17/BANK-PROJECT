package bank.project.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//loan POJO class
public class LoanScheme {
    private int loanSchemeId;
    private String loanSchemeType;
    private String loanSchemeName;
    private String loanSchemeDesc;
    private float  loanSchemeRoi;
}
