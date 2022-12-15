package org.zerock.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("normal")
@Repository
public class SampleDAOImpl {
}
