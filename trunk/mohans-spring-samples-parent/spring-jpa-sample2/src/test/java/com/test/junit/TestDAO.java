package com.test.junit;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.conversion.service.FileReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestDAO {

    @Inject
    private FileReader fileReader;

    @Test
    public void testPostStorage() throws Exception {
	fileReader
		.populateAccessCodeFileData("./file/sample-conversion-file.csv");

    }

}
