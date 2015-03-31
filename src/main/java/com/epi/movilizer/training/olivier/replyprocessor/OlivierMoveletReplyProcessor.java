package com.epi.movilizer.training.olivier.replyprocessor;

import com.epi.movilizer.training.olivier.JdbcUtils;
import com.movilitas.movilizer.v12.MovilizerReplyMovelet;
import com.movilizer.pull.CannotProcessReplyMoveletException;
import com.movilizer.pull.IReplyMoveletProcessor;
import com.movilizer.util.config.IMovilizerConfig;
import com.movilizer.util.config.MovilizerConfig;
import com.movilizer.util.logger.ComponentLogger;
import com.movilizer.util.logger.ILogger;
import com.movilizer.util.movelet.reply.IReplyAnswerMap;
import com.movilizer.util.movelet.reply.ReplyAnswerValueCannotBeRetrievedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.movilizer.util.movelet.reply.ReplyMoveletUtils.collectAnswers;
import static java.lang.String.format;

/**
 * @author Peter.Grigoriev@movilizer.com
 */
public class OlivierMoveletReplyProcessor implements IReplyMoveletProcessor{

    public static final String SQL = "INSERT INTO MOVILIZER.MOVELET_REPLY (FULL_TEXT, DEVICE_ADDRESS)  VALUES (?, ?)";

    public boolean canProcessReplyMovelet(MovilizerReplyMovelet replyMovelet) {
        return "OlivierMovelet".equals(replyMovelet.getMoveletKey());
    }

    public void processReplyMovelet(MovilizerReplyMovelet replyMovelet) throws CannotProcessReplyMoveletException {
        IReplyAnswerMap answerMap = collectAnswers(replyMovelet);
        try {
            String text = answerMap.getRequiredString("A_FullText");
            String deviceAddress = replyMovelet.getDeviceAddress();

            save(text, deviceAddress);

        } catch (ReplyAnswerValueCannotBeRetrievedException e) {
            System.out.println("Uups!!!");
        } catch (SQLException e) {
            System.out.println("Uups .. sql exception !!!");
        }

    }

    private void save(String text, String deviceAddress) throws SQLException {
        IMovilizerConfig config = MovilizerConfig.getInstance(OlivierMoveletReplyProcessor.class);
        Connection connection = JdbcUtils.getConnection(config.getJdbcSettings());
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, text);
        preparedStatement.setString(2, deviceAddress);
        preparedStatement.execute();
    }
}
