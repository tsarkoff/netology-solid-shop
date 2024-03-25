package companies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Company {
    private String name;
    private String address;
    private String taxID;
}
