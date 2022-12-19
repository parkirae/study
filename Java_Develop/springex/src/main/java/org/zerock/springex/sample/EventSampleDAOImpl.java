package org.zerock.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Qualifier("event")
@Repository
public class EventSampleDAOImpl implements SampleDAO{
}
