package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Transaction {
    private String bId;
    private String bName;
    private String genre;
    private String mId;
    private String bdate;
    private String dueDate;
}
