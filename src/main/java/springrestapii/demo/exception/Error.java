package springrestapii.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Long errorCode;

}
