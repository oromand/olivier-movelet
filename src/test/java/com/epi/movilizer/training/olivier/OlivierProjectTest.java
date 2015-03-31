package com.epi.movilizer.training.olivier;

import com.movilitas.movilizer.v12.MovilizerMoveletSet;
import com.movilizer.connector.MovilizerCloudSystem;
import com.movilizer.connector.mock.MockMovilizerRequestSender;
import com.movilizer.push.MovilizerPushCall;
import com.movilizer.util.template.ITemplateRepository;
import com.movilizer.util.template.ResourceXmlTemplateRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class OlivierProjectTest {


    private OlivierProject instance;

    @BeforeMethod
    public void setUp() throws Exception {
        instance = new OlivierProject();
        assertNotNull(instance);
    }

    @Test
    public void testOnInitProject() throws Exception {
        MovilizerCloudSystem system = new MovilizerCloudSystem();
        MockMovilizerRequestSender requestSender = new MockMovilizerRequestSender();
        ITemplateRepository templateRepository = new ResourceXmlTemplateRepository("Olivier", OlivierProject.class);

        MovilizerPushCall pushCall = new MovilizerPushCall(system, null, requestSender, null, templateRepository);
        this.instance.onInitProject(pushCall);

        List<MovilizerMoveletSet> moveletSet = pushCall.accessRequest().getMoveletSet();
        assertFalse(moveletSet.isEmpty());
    }
}