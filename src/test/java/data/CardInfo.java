package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {
  private String number;
  private String year;
  private String month;
  private String holder;
  private String cvc;
}
