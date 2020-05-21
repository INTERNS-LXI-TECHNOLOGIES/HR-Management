package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.AppraisalApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the AppraisalControllerResource REST controller.
 *
 * @see AppraisalControllerResource
 */
@SpringBootTest(classes = AppraisalApp.class)
public class AppraisalControllerResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        AppraisalControllerResource appraisalControllerResource = new AppraisalControllerResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(appraisalControllerResource)
            .build();
    }

    /**
     * Test defaultAction
     */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/appraisal-controller-resource/default-action"))
            .andExpect(status().isOk());
    }
}
