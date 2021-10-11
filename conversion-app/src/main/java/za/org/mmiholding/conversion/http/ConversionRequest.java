package za.org.mmiholding.conversion.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRequest {

    private String type;
    private String from;
    private String to;
    private double value;
}

