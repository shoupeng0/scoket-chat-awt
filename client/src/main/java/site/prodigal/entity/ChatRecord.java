package site.prodigal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {

    private Integer id;
    private String sendUsername;
    private String receiveUsername;
    private String message;

}
