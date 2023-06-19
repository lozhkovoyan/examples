/*
package ru.fssp.odpea;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.fssp.odpea.controller.ModelController;
import ru.fssp.odpea.service.ModelInterface;
//import ru.fssp.odpea.service.impl.ModelService;
import ru.fssp.odpea.model.ModelName;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ModelNameApplicationTests {
    //    OdpeaApplication odpeaApplication = new OdpeaApplication();
    @Autowired
    public MockMvc mockMvc;
    @Mock
    private ModelInterface myService;
//
//    @InjectMocks
//    private ModelController myController;


    */
/* @BeforeEach
     void prepare() {

         ModelName modelName = new ModelName(
                 1l,
                 "Test",
                 "Aspect",
                 ZonedDateTime.now(),
                 ZonedDateTime.parse("2019-03-27 10:15:30 AM",
                         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")),
                 'Y',
                 null,
                 )

         dto = supplier.supplyHistoryDto(22L, 22L, 33L,
                 44L, null, 66L, 77L);
     }
 *//*

    @Before
    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();

    }

    @Test
    @DisplayName("Обновление юзера")
    public void updateTest() throws Exception {
//        HistoryDto historyDto = historyMapper.toDto(historyMapper.mergeToEntity(dto, entity));
//
//        doReturn(historyDto).when(historyService).update(any(Long.class), any(H

    }

    @Test
    public void testCreateData() throws Exception {

// Arrange
*/
/*        ModelName testData = new ModelName();
        testData.setId(1L);
        testData.setType("Test Data");
        testData.setDtEnd(ZonedDateTime.now());
        testData.setDataCreate(ZonedDateTime.parse("2019-03-27 10:15:30 AM", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")));
        testData.setValueNameFirm("Aspect");
        testData.setIsNowActive('Y');
        testData.setValueIsteadNameFirm(null);
        testData.setUserCreate("User1");
        testData.setDtBeg(ZonedDateTime.now());*//*

*/
/*String timeStamp = "2019-03-27T10:15:30";
ZonedDateTime localTimeObj = ZonedDateTime.parse(time);

//2 - specified pattern
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
String timeStamp1 = "2019-03-27 10:15:30 AM";
ZonedDateTime localTimeObj1 = ZonedDateTime.parse(timeStamp1, formatter);

ZonedDateTime now = ZonedDateTime.now();
ZonedDateTime now = ZonedDateTime.now( ZoneId.of("GMT+05:30") );*//*

//        doReturn()
        when(myService.save(any())).thenReturn(testData);

        MvcResult result = mockMvc.perform((RequestBuilder) post("/api/data")
                        .contentType(MediaType.APPLICATION_JSON))
//                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id").value(testData.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(testData.getValueNameFirm()))
                .andReturn();

// Assert
//        verify(myService, times(1)).saveData(any(Data.class));
    }

    @Test
    @DisplayName("Сохранение модель ModelName")
    public void createTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ModelName testData = new ModelName();
        doReturn(testData).when(myService).save(any(ModelName.class));

        mockMvc.perform((RequestBuilder) post("/create")
//                        .content(testData.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").isNumber())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1L));
    }
}
*/
/*private static final Long id = 22L;
    private final MockMvc mockMvc;
    private final HistoryMapper historyMapper = new HistoryMapperImpl();
    @InjectMocks
    private static HistorySupplier supplier;
    @MockBean
    private HistoryServiceImpl historyService;
    private HistoryEntity entity;
    private HistoryDto dto;
    @InjectMocks
    private ObjectMapper objectMapper;*/

