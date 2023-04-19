package bank.project.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//banksceheme POJO class
public class BankScheme {
    private int loanSchemeId;
    private String loanSchemeType;
    private String loanSchemeName;
    private String loanSchemeDesc;
    private float loanSchemeRoi;
}
