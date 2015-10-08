package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@ActiveProfiles("default,test")
public class DemoApplicationTests {

	@Autowired
	SomeSectionSettings someSectionSettings;

	@Test
	public void testProp1() {
		Assert.assertEquals("300",someSectionSettings.getProp1());
	}

}
