package com.kim.email;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmailMessage {
    private String to;
    private String subject;
    private String message;
}
