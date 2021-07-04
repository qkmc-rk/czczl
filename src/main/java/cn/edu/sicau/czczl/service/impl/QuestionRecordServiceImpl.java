package cn.edu.sicau.czczl.service.impl;//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                           O\  =  /O
//                        ____/`---'\____
//                      .'  \\|     |//  `.
//                     /  \\|||  :  |||//  \
//                    /  _||||| -:- |||||-  \
//                    |   | \\\  -  /// |   |
//                    | \_|  ''\---/''  |   |
//                    \  .-\__  `-`  ___/-. /
//                  ___`. .'  /--.--\  `. . __
//               ."" '<  `.___\_<|>_/___.'  >'"".
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//              \  \ `-.   \_ __\ /__ _/   .-` /  /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                      Buddha Bless, No Bug !

import cn.edu.sicau.czczl.entity.QuestionRecord;
import cn.edu.sicau.czczl.repository.QuestionRecordRepository;
import cn.edu.sicau.czczl.service.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-07-04 17:26
 */
@Service
public class QuestionRecordServiceImpl implements QuestionRecordService {

    @Autowired
    QuestionRecordRepository questionRecordRepository;

    @Override
    public QuestionRecord saveQuestionRecord(QuestionRecord questionRecord) {
        return questionRecordRepository.saveAndFlush(questionRecord);
    }
}
