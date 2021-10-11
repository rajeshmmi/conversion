package za.org.mmiholding.conversion.http;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse 
{
	//General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
}