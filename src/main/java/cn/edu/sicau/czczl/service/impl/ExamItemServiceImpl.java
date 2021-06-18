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

import cn.edu.sicau.czczl.entity.ExamItem;
import cn.edu.sicau.czczl.repository.ExamItemRepository;
import cn.edu.sicau.czczl.service.ExamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-09 12:35
 */
@Service
public class ExamItemServiceImpl implements ExamItemService {

    @Autowired
    private ExamItemRepository examItemRepository;
    @Override
    public List<ExamItem> findQuestionByMaterialId(Long materialid) {
        List<ExamItem> examItemList=examItemRepository.findExamItemByMaterialId(materialid);
        return examItemList;
    }
}
