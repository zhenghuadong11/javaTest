package MVC;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Hello {

   private String name;
   private String user;
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Date date;
}
