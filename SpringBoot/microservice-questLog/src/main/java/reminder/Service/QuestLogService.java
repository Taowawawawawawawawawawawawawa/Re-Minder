// package reminder.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import reminder.domain.QuestLog;
// import reminder.dto.QuestLogDTO;
// import reminder.repository.QuestLogRepository;

// // @Service
// // public class QuestLogService {

// //     @Autowired
// //     private QuestLogRepository questLogRepository; // Repository สำหรับดึงข้อมูลจากฐานข้อมูล

// //     public List<QuestLogDTO> getAllQuestLogs() {
// //         List<QuestLog> questLogs = questLogRepository.findAll(); // ดึงข้อมูลทั้งหมด
// //         return questLogs.stream()
// //                 .map(log -> new QuestLogDTO(
// //                         log.getId(),
// //                         log.getQuestName(),
// //                         log.getStatus(),
// //                         log.getSubmittedImage(),
// //                         log.getMessage()
// //                 ))
// //                 .collect(Collectors.toList());
// //     }
// }
