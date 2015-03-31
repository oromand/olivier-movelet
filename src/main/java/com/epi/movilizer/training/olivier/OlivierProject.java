package com.epi.movilizer.training.olivier;

import com.epi.movilizer.training.olivier.replyprocessor.OlivierMoveletReplyProcessor;
import com.movilizer.connector.IMoveletKeyWithExtension;
import com.movilizer.projectmanagement.MovilizerProjectBase;
import com.movilizer.pull.IReplyMoveletProcessor;
import com.movilizer.push.IMovilizerPushCall;
import com.movilizer.push.IMovilizerPushCallListener;
import com.movilizer.usermanagement.IMovilizerUser;

import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.movilizer.connector.MoveletKeyWithExtension.keyWithExtension;
import static java.util.Arrays.asList;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class OlivierProject extends MovilizerProjectBase{

    public static final IMoveletKeyWithExtension INTERVENTION_LIST_MOVELET_KEY =  keyWithExtension("OlivierMovelet", "");

    public OlivierProject() {
        super("olivier-movelet", 12);
    }

    public void onInitProject(IMovilizerPushCall pushCall) throws Exception {
        OlivierMoveletDataProvider iMoveletDataProvider = new OlivierMoveletDataProvider();
        pushCall.addMovelets(iMoveletDataProvider, "olivier-movelet.vm");
    }

    public void onShutdownProject(IMovilizerPushCall iMovilizerPushCall) {

    }

    public void onUsersAssigned(Collection<IMovilizerUser> users, IMovilizerPushCall pushCall) {
       pushCall.addAssignments(users, asList(INTERVENTION_LIST_MOVELET_KEY));
    }

    public void onUsersUnassigned(Collection<IMovilizerUser> collection, IMovilizerPushCall iMovilizerPushCall) {

    }

    public IMovilizerPushCallListener onPushCallAvailable(IMovilizerPushCall iMovilizerPushCall) throws Exception {
        return null;
    }

    @Override
    public Set<IReplyMoveletProcessor> getReplyProcessors() {
        IReplyMoveletProcessor olivierMoveletReplyProcessor = new OlivierMoveletReplyProcessor();
        return newHashSet(olivierMoveletReplyProcessor);
    }
}
