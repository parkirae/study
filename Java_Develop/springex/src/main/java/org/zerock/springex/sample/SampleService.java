package org.zerock.springex.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
public class SampleService {

    @Autowired
    private SampleDAO sampleDAO;
}
