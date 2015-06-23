
package com.thed.service.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.thed.service.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTestcasesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getTestcasesByCriteria");
    private final static QName _UpdateAttachmentResponse_QNAME = new QName("http://soap.service.thed.com/", "updateAttachmentResponse");
    private final static QName _AddAttachments_QNAME = new QName("http://soap.service.thed.com/", "addAttachments");
    private final static QName _LoginResponse_QNAME = new QName("http://soap.service.thed.com/", "loginResponse");
    private final static QName _GetUserById_QNAME = new QName("http://soap.service.thed.com/", "getUserById");
    private final static QName _GetTestcaseTreeByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestcaseTreeByIdResponse");
    private final static QName _GetTestCaseTreesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestCaseTreesByCriteriaResponse");
    private final static QName _GetCycleById_QNAME = new QName("http://soap.service.thed.com/", "getCycleById");
    private final static QName _CreateNewRequirementResponse_QNAME = new QName("http://soap.service.thed.com/", "createNewRequirementResponse");
    private final static QName _DeleteAttachmentById_QNAME = new QName("http://soap.service.thed.com/", "deleteAttachmentById");
    private final static QName _GetTestcaseById_QNAME = new QName("http://soap.service.thed.com/", "getTestcaseById");
    private final static QName _GetCustomFields_QNAME = new QName("http://soap.service.thed.com/", "getCustomFields");
    private final static QName _CreateNewCycleResponse_QNAME = new QName("http://soap.service.thed.com/", "createNewCycleResponse");
    private final static QName _Logout_QNAME = new QName("http://soap.service.thed.com/", "logout");
    private final static QName _UpdateAttachment_QNAME = new QName("http://soap.service.thed.com/", "updateAttachment");
    private final static QName _RemoteRelease_QNAME = new QName("http://soap.service.thed.com/", "remoteRelease");
    private final static QName _GetRequirementTreesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getRequirementTreesByCriteria");
    private final static QName _AssignTestSchedulesResponse_QNAME = new QName("http://soap.service.thed.com/", "assignTestSchedulesResponse");
    private final static QName _GetRequirementsByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getRequirementsByCriteria");
    private final static QName _GetUsersByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getUsersByCriteriaResponse");
    private final static QName _CreateNewRequirementTreeResponse_QNAME = new QName("http://soap.service.thed.com/", "createNewRequirementTreeResponse");
    private final static QName _GetRequirementTreeByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getRequirementTreeByIdResponse");
    private final static QName _AssignTestSchedules_QNAME = new QName("http://soap.service.thed.com/", "assignTestSchedules");
    private final static QName _GetRequirementTreeById_QNAME = new QName("http://soap.service.thed.com/", "getRequirementTreeById");
    private final static QName _RemoteUser_QNAME = new QName("http://soap.service.thed.com/", "remoteUser");
    private final static QName _DeleteAttachmentsByCriteria_QNAME = new QName("http://soap.service.thed.com/", "deleteAttachmentsByCriteria");
    private final static QName _CreateNewTestcaseResponse_QNAME = new QName("http://soap.service.thed.com/", "createNewTestcaseResponse");
    private final static QName _GetTestSchedulesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestSchedulesByCriteriaResponse");
    private final static QName _CreateNewTestcaseTreeResponse_QNAME = new QName("http://soap.service.thed.com/", "createNewTestcaseTreeResponse");
    private final static QName _RemoteTestResult_QNAME = new QName("http://soap.service.thed.com/", "remoteTestResult");
    private final static QName _RemoteProject_QNAME = new QName("http://soap.service.thed.com/", "remoteProject");
    private final static QName _CreateNewRequirement_QNAME = new QName("http://soap.service.thed.com/", "createNewRequirement");
    private final static QName _RemoteProjectTeam_QNAME = new QName("http://soap.service.thed.com/", "remoteProjectTeam");
    private final static QName _GetRequirementsByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getRequirementsByCriteriaResponse");
    private final static QName _RemoteAttachment_QNAME = new QName("http://soap.service.thed.com/", "remoteAttachment");
    private final static QName _GetAttachmentByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getAttachmentByIdResponse");
    private final static QName _CreateNewCycle_QNAME = new QName("http://soap.service.thed.com/", "createNewCycle");
    private final static QName _GetTestSchedulesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getTestSchedulesByCriteria");
    private final static QName _GetReleaseByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getReleaseByIdResponse");
    private final static QName _GetProjectByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getProjectByIdResponse");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getUserByIdResponse");
    private final static QName _AddPhaseToCycle_QNAME = new QName("http://soap.service.thed.com/", "addPhaseToCycle");
    private final static QName _GetProjectsByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getProjectsByCriteria");
    private final static QName _GetTestcaseTreeById_QNAME = new QName("http://soap.service.thed.com/", "getTestcaseTreeById");
    private final static QName _RemoteCriteria_QNAME = new QName("http://soap.service.thed.com/", "remoteCriteria");
    private final static QName _GetReleasesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getReleasesByCriteria");
    private final static QName _GetTestcaseByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestcaseByIdResponse");
    private final static QName _GetAttachmentsByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getAttachmentsByCriteria");
    private final static QName _GetReleaseById_QNAME = new QName("http://soap.service.thed.com/", "getReleaseById");
    private final static QName _UpdateRequirementResponse_QNAME = new QName("http://soap.service.thed.com/", "updateRequirementResponse");
    private final static QName _GetUsersByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getUsersByCriteria");
    private final static QName _AddPhaseToCycleResponse_QNAME = new QName("http://soap.service.thed.com/", "addPhaseToCycleResponse");
    private final static QName _RemoteRepositoryTreeTestcase_QNAME = new QName("http://soap.service.thed.com/", "remoteRepositoryTreeTestcase");
    private final static QName _UpdateTestStatus_QNAME = new QName("http://soap.service.thed.com/", "updateTestStatus");
    private final static QName _RemoteDepartment_QNAME = new QName("http://soap.service.thed.com/", "remoteDepartment");
    private final static QName _GetCustomFieldsResponse_QNAME = new QName("http://soap.service.thed.com/", "getCustomFieldsResponse");
    private final static QName _AddAttachmentsResponse_QNAME = new QName("http://soap.service.thed.com/", "addAttachmentsResponse");
    private final static QName _GetCycleByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getCycleByIdResponse");
    private final static QName _GetReleasesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getReleasesByCriteriaResponse");
    private final static QName _GetTestCaseTreesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getTestCaseTreesByCriteria");
    private final static QName _UpdateRequirement_QNAME = new QName("http://soap.service.thed.com/", "updateRequirement");
    private final static QName _UpdateTestcase_QNAME = new QName("http://soap.service.thed.com/", "updateTestcase");
    private final static QName _RemoteFieldValue_QNAME = new QName("http://soap.service.thed.com/", "remoteFieldValue");
    private final static QName _GetRequirementByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getRequirementByIdResponse");
    private final static QName _GetTestcasesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestcasesByCriteriaResponse");
    private final static QName _GetCyclesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getCyclesByCriteriaResponse");
    private final static QName _GetAttachmentById_QNAME = new QName("http://soap.service.thed.com/", "getAttachmentById");
    private final static QName _Exception_QNAME = new QName("http://soap.service.thed.com/", "exception");
    private final static QName _CreateNewRequirementTree_QNAME = new QName("http://soap.service.thed.com/", "createNewRequirementTree");
    private final static QName _CreateNewTestcaseTree_QNAME = new QName("http://soap.service.thed.com/", "createNewTestcaseTree");
    private final static QName _GetTestSchedulesById_QNAME = new QName("http://soap.service.thed.com/", "getTestSchedulesById");
    private final static QName _UpdateTestStatusResponse_QNAME = new QName("http://soap.service.thed.com/", "updateTestStatusResponse");
    private final static QName _GetRequirementTreesByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getRequirementTreesByCriteriaResponse");
    private final static QName _GetAttachmentsByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getAttachmentsByCriteriaResponse");
    private final static QName _GetCyclesByCriteria_QNAME = new QName("http://soap.service.thed.com/", "getCyclesByCriteria");
    private final static QName _CreateNewTestcase_QNAME = new QName("http://soap.service.thed.com/", "createNewTestcase");
    private final static QName _GetRequirementById_QNAME = new QName("http://soap.service.thed.com/", "getRequirementById");
    private final static QName _GetProjectById_QNAME = new QName("http://soap.service.thed.com/", "getProjectById");
    private final static QName _GetTestSchedulesByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "getTestSchedulesByIdResponse");
    private final static QName _DeleteAttachmentByIdResponse_QNAME = new QName("http://soap.service.thed.com/", "deleteAttachmentByIdResponse");
    private final static QName _GetProjectsByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "getProjectsByCriteriaResponse");
    private final static QName _UpdateTestcaseResponse_QNAME = new QName("http://soap.service.thed.com/", "updateTestcaseResponse");
    private final static QName _Login_QNAME = new QName("http://soap.service.thed.com/", "login");
    private final static QName _DeleteAttachmentsByCriteriaResponse_QNAME = new QName("http://soap.service.thed.com/", "deleteAttachmentsByCriteriaResponse");
    private final static QName _LogoutResponse_QNAME = new QName("http://soap.service.thed.com/", "logoutResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.thed.service.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoteCustomizableEntity }
     * 
     */
    public RemoteCustomizableEntity createRemoteCustomizableEntity() {
        return new RemoteCustomizableEntity();
    }

    /**
     * Create an instance of {@link RemoteCustomizableEntity.CustomProperties }
     * 
     */
    public RemoteCustomizableEntity.CustomProperties createRemoteCustomizableEntityCustomProperties() {
        return new RemoteCustomizableEntity.CustomProperties();
    }

    /**
     * Create an instance of {@link GetAttachmentById }
     * 
     */
    public GetAttachmentById createGetAttachmentById() {
        return new GetAttachmentById();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CreateNewTestcaseTree }
     * 
     */
    public CreateNewTestcaseTree createCreateNewTestcaseTree() {
        return new CreateNewTestcaseTree();
    }

    /**
     * Create an instance of {@link CreateNewRequirementTree }
     * 
     */
    public CreateNewRequirementTree createCreateNewRequirementTree() {
        return new CreateNewRequirementTree();
    }

    /**
     * Create an instance of {@link UpdateTestStatusResponse }
     * 
     */
    public UpdateTestStatusResponse createUpdateTestStatusResponse() {
        return new UpdateTestStatusResponse();
    }

    /**
     * Create an instance of {@link GetRequirementTreesByCriteriaResponse }
     * 
     */
    public GetRequirementTreesByCriteriaResponse createGetRequirementTreesByCriteriaResponse() {
        return new GetRequirementTreesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetTestSchedulesById }
     * 
     */
    public GetTestSchedulesById createGetTestSchedulesById() {
        return new GetTestSchedulesById();
    }

    /**
     * Create an instance of {@link CreateNewTestcase }
     * 
     */
    public CreateNewTestcase createCreateNewTestcase() {
        return new CreateNewTestcase();
    }

    /**
     * Create an instance of {@link GetAttachmentsByCriteriaResponse }
     * 
     */
    public GetAttachmentsByCriteriaResponse createGetAttachmentsByCriteriaResponse() {
        return new GetAttachmentsByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetCyclesByCriteria }
     * 
     */
    public GetCyclesByCriteria createGetCyclesByCriteria() {
        return new GetCyclesByCriteria();
    }

    /**
     * Create an instance of {@link GetTestSchedulesByIdResponse }
     * 
     */
    public GetTestSchedulesByIdResponse createGetTestSchedulesByIdResponse() {
        return new GetTestSchedulesByIdResponse();
    }

    /**
     * Create an instance of {@link DeleteAttachmentByIdResponse }
     * 
     */
    public DeleteAttachmentByIdResponse createDeleteAttachmentByIdResponse() {
        return new DeleteAttachmentByIdResponse();
    }

    /**
     * Create an instance of {@link GetProjectById }
     * 
     */
    public GetProjectById createGetProjectById() {
        return new GetProjectById();
    }

    /**
     * Create an instance of {@link GetRequirementById }
     * 
     */
    public GetRequirementById createGetRequirementById() {
        return new GetRequirementById();
    }

    /**
     * Create an instance of {@link GetProjectsByCriteriaResponse }
     * 
     */
    public GetProjectsByCriteriaResponse createGetProjectsByCriteriaResponse() {
        return new GetProjectsByCriteriaResponse();
    }

    /**
     * Create an instance of {@link UpdateTestcaseResponse }
     * 
     */
    public UpdateTestcaseResponse createUpdateTestcaseResponse() {
        return new UpdateTestcaseResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link DeleteAttachmentsByCriteriaResponse }
     * 
     */
    public DeleteAttachmentsByCriteriaResponse createDeleteAttachmentsByCriteriaResponse() {
        return new DeleteAttachmentsByCriteriaResponse();
    }

    /**
     * Create an instance of {@link AddPhaseToCycle }
     * 
     */
    public AddPhaseToCycle createAddPhaseToCycle() {
        return new AddPhaseToCycle();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     * 
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link RemoteCriteria }
     * 
     */
    public RemoteCriteria createRemoteCriteria() {
        return new RemoteCriteria();
    }

    /**
     * Create an instance of {@link GetTestcaseTreeById }
     * 
     */
    public GetTestcaseTreeById createGetTestcaseTreeById() {
        return new GetTestcaseTreeById();
    }

    /**
     * Create an instance of {@link GetProjectsByCriteria }
     * 
     */
    public GetProjectsByCriteria createGetProjectsByCriteria() {
        return new GetProjectsByCriteria();
    }

    /**
     * Create an instance of {@link GetReleasesByCriteria }
     * 
     */
    public GetReleasesByCriteria createGetReleasesByCriteria() {
        return new GetReleasesByCriteria();
    }

    /**
     * Create an instance of {@link GetTestcaseByIdResponse }
     * 
     */
    public GetTestcaseByIdResponse createGetTestcaseByIdResponse() {
        return new GetTestcaseByIdResponse();
    }

    /**
     * Create an instance of {@link GetAttachmentsByCriteria }
     * 
     */
    public GetAttachmentsByCriteria createGetAttachmentsByCriteria() {
        return new GetAttachmentsByCriteria();
    }

    /**
     * Create an instance of {@link GetReleaseById }
     * 
     */
    public GetReleaseById createGetReleaseById() {
        return new GetReleaseById();
    }

    /**
     * Create an instance of {@link AddPhaseToCycleResponse }
     * 
     */
    public AddPhaseToCycleResponse createAddPhaseToCycleResponse() {
        return new AddPhaseToCycleResponse();
    }

    /**
     * Create an instance of {@link UpdateRequirementResponse }
     * 
     */
    public UpdateRequirementResponse createUpdateRequirementResponse() {
        return new UpdateRequirementResponse();
    }

    /**
     * Create an instance of {@link GetUsersByCriteria }
     * 
     */
    public GetUsersByCriteria createGetUsersByCriteria() {
        return new GetUsersByCriteria();
    }

    /**
     * Create an instance of {@link RemoteRepositoryTreeTestcase }
     * 
     */
    public RemoteRepositoryTreeTestcase createRemoteRepositoryTreeTestcase() {
        return new RemoteRepositoryTreeTestcase();
    }

    /**
     * Create an instance of {@link UpdateTestStatus }
     * 
     */
    public UpdateTestStatus createUpdateTestStatus() {
        return new UpdateTestStatus();
    }

    /**
     * Create an instance of {@link RemoteDepartment }
     * 
     */
    public RemoteDepartment createRemoteDepartment() {
        return new RemoteDepartment();
    }

    /**
     * Create an instance of {@link GetCustomFieldsResponse }
     * 
     */
    public GetCustomFieldsResponse createGetCustomFieldsResponse() {
        return new GetCustomFieldsResponse();
    }

    /**
     * Create an instance of {@link GetTestCaseTreesByCriteria }
     * 
     */
    public GetTestCaseTreesByCriteria createGetTestCaseTreesByCriteria() {
        return new GetTestCaseTreesByCriteria();
    }

    /**
     * Create an instance of {@link AddAttachmentsResponse }
     * 
     */
    public AddAttachmentsResponse createAddAttachmentsResponse() {
        return new AddAttachmentsResponse();
    }

    /**
     * Create an instance of {@link GetCycleByIdResponse }
     * 
     */
    public GetCycleByIdResponse createGetCycleByIdResponse() {
        return new GetCycleByIdResponse();
    }

    /**
     * Create an instance of {@link GetReleasesByCriteriaResponse }
     * 
     */
    public GetReleasesByCriteriaResponse createGetReleasesByCriteriaResponse() {
        return new GetReleasesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link UpdateTestcase }
     * 
     */
    public UpdateTestcase createUpdateTestcase() {
        return new UpdateTestcase();
    }

    /**
     * Create an instance of {@link UpdateRequirement }
     * 
     */
    public UpdateRequirement createUpdateRequirement() {
        return new UpdateRequirement();
    }

    /**
     * Create an instance of {@link GetRequirementByIdResponse }
     * 
     */
    public GetRequirementByIdResponse createGetRequirementByIdResponse() {
        return new GetRequirementByIdResponse();
    }

    /**
     * Create an instance of {@link RemoteFieldValue }
     * 
     */
    public RemoteFieldValue createRemoteFieldValue() {
        return new RemoteFieldValue();
    }

    /**
     * Create an instance of {@link GetTestcasesByCriteriaResponse }
     * 
     */
    public GetTestcasesByCriteriaResponse createGetTestcasesByCriteriaResponse() {
        return new GetTestcasesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetCyclesByCriteriaResponse }
     * 
     */
    public GetCyclesByCriteriaResponse createGetCyclesByCriteriaResponse() {
        return new GetCyclesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link DeleteAttachmentsByCriteria }
     * 
     */
    public DeleteAttachmentsByCriteria createDeleteAttachmentsByCriteria() {
        return new DeleteAttachmentsByCriteria();
    }

    /**
     * Create an instance of {@link CreateNewTestcaseResponse }
     * 
     */
    public CreateNewTestcaseResponse createCreateNewTestcaseResponse() {
        return new CreateNewTestcaseResponse();
    }

    /**
     * Create an instance of {@link RemoteTestResult }
     * 
     */
    public RemoteTestResult createRemoteTestResult() {
        return new RemoteTestResult();
    }

    /**
     * Create an instance of {@link CreateNewTestcaseTreeResponse }
     * 
     */
    public CreateNewTestcaseTreeResponse createCreateNewTestcaseTreeResponse() {
        return new CreateNewTestcaseTreeResponse();
    }

    /**
     * Create an instance of {@link GetTestSchedulesByCriteriaResponse }
     * 
     */
    public GetTestSchedulesByCriteriaResponse createGetTestSchedulesByCriteriaResponse() {
        return new GetTestSchedulesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link RemoteProject }
     * 
     */
    public RemoteProject createRemoteProject() {
        return new RemoteProject();
    }

    /**
     * Create an instance of {@link CreateNewRequirement }
     * 
     */
    public CreateNewRequirement createCreateNewRequirement() {
        return new CreateNewRequirement();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link GetRequirementsByCriteriaResponse }
     * 
     */
    public GetRequirementsByCriteriaResponse createGetRequirementsByCriteriaResponse() {
        return new GetRequirementsByCriteriaResponse();
    }

    /**
     * Create an instance of {@link RemoteAttachment }
     * 
     */
    public RemoteAttachment createRemoteAttachment() {
        return new RemoteAttachment();
    }

    /**
     * Create an instance of {@link GetAttachmentByIdResponse }
     * 
     */
    public GetAttachmentByIdResponse createGetAttachmentByIdResponse() {
        return new GetAttachmentByIdResponse();
    }

    /**
     * Create an instance of {@link GetTestSchedulesByCriteria }
     * 
     */
    public GetTestSchedulesByCriteria createGetTestSchedulesByCriteria() {
        return new GetTestSchedulesByCriteria();
    }

    /**
     * Create an instance of {@link CreateNewCycle }
     * 
     */
    public CreateNewCycle createCreateNewCycle() {
        return new CreateNewCycle();
    }

    /**
     * Create an instance of {@link GetProjectByIdResponse }
     * 
     */
    public GetProjectByIdResponse createGetProjectByIdResponse() {
        return new GetProjectByIdResponse();
    }

    /**
     * Create an instance of {@link GetReleaseByIdResponse }
     * 
     */
    public GetReleaseByIdResponse createGetReleaseByIdResponse() {
        return new GetReleaseByIdResponse();
    }

    /**
     * Create an instance of {@link GetTestcasesByCriteria }
     * 
     */
    public GetTestcasesByCriteria createGetTestcasesByCriteria() {
        return new GetTestcasesByCriteria();
    }

    /**
     * Create an instance of {@link AddAttachments }
     * 
     */
    public AddAttachments createAddAttachments() {
        return new AddAttachments();
    }

    /**
     * Create an instance of {@link UpdateAttachmentResponse }
     * 
     */
    public UpdateAttachmentResponse createUpdateAttachmentResponse() {
        return new UpdateAttachmentResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetUserById }
     * 
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetTestCaseTreesByCriteriaResponse }
     * 
     */
    public GetTestCaseTreesByCriteriaResponse createGetTestCaseTreesByCriteriaResponse() {
        return new GetTestCaseTreesByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetTestcaseTreeByIdResponse }
     * 
     */
    public GetTestcaseTreeByIdResponse createGetTestcaseTreeByIdResponse() {
        return new GetTestcaseTreeByIdResponse();
    }

    /**
     * Create an instance of {@link CreateNewRequirementResponse }
     * 
     */
    public CreateNewRequirementResponse createCreateNewRequirementResponse() {
        return new CreateNewRequirementResponse();
    }

    /**
     * Create an instance of {@link GetCycleById }
     * 
     */
    public GetCycleById createGetCycleById() {
        return new GetCycleById();
    }

    /**
     * Create an instance of {@link GetTestcaseById }
     * 
     */
    public GetTestcaseById createGetTestcaseById() {
        return new GetTestcaseById();
    }

    /**
     * Create an instance of {@link DeleteAttachmentById }
     * 
     */
    public DeleteAttachmentById createDeleteAttachmentById() {
        return new DeleteAttachmentById();
    }

    /**
     * Create an instance of {@link CreateNewCycleResponse }
     * 
     */
    public CreateNewCycleResponse createCreateNewCycleResponse() {
        return new CreateNewCycleResponse();
    }

    /**
     * Create an instance of {@link GetCustomFields }
     * 
     */
    public GetCustomFields createGetCustomFields() {
        return new GetCustomFields();
    }

    /**
     * Create an instance of {@link UpdateAttachment }
     * 
     */
    public UpdateAttachment createUpdateAttachment() {
        return new UpdateAttachment();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link RemoteRelease }
     * 
     */
    public RemoteRelease createRemoteRelease() {
        return new RemoteRelease();
    }

    /**
     * Create an instance of {@link GetRequirementTreesByCriteria }
     * 
     */
    public GetRequirementTreesByCriteria createGetRequirementTreesByCriteria() {
        return new GetRequirementTreesByCriteria();
    }

    /**
     * Create an instance of {@link GetUsersByCriteriaResponse }
     * 
     */
    public GetUsersByCriteriaResponse createGetUsersByCriteriaResponse() {
        return new GetUsersByCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetRequirementsByCriteria }
     * 
     */
    public GetRequirementsByCriteria createGetRequirementsByCriteria() {
        return new GetRequirementsByCriteria();
    }

    /**
     * Create an instance of {@link AssignTestSchedulesResponse }
     * 
     */
    public AssignTestSchedulesResponse createAssignTestSchedulesResponse() {
        return new AssignTestSchedulesResponse();
    }

    /**
     * Create an instance of {@link AssignTestSchedules }
     * 
     */
    public AssignTestSchedules createAssignTestSchedules() {
        return new AssignTestSchedules();
    }

    /**
     * Create an instance of {@link GetRequirementTreeByIdResponse }
     * 
     */
    public GetRequirementTreeByIdResponse createGetRequirementTreeByIdResponse() {
        return new GetRequirementTreeByIdResponse();
    }

    /**
     * Create an instance of {@link CreateNewRequirementTreeResponse }
     * 
     */
    public CreateNewRequirementTreeResponse createCreateNewRequirementTreeResponse() {
        return new CreateNewRequirementTreeResponse();
    }

    /**
     * Create an instance of {@link RemoteUser }
     * 
     */
    public RemoteUser createRemoteUser() {
        return new RemoteUser();
    }

    /**
     * Create an instance of {@link GetRequirementTreeById }
     * 
     */
    public GetRequirementTreeById createGetRequirementTreeById() {
        return new GetRequirementTreeById();
    }

    /**
     * Create an instance of {@link RemoteTestcase }
     * 
     */
    public RemoteTestcase createRemoteTestcase() {
        return new RemoteTestcase();
    }

    /**
     * Create an instance of {@link RemoteCycle }
     * 
     */
    public RemoteCycle createRemoteCycle() {
        return new RemoteCycle();
    }

    /**
     * Create an instance of {@link TestResult }
     * 
     */
    public TestResult createTestResult() {
        return new TestResult();
    }

    /**
     * Create an instance of {@link RemotePhase }
     * 
     */
    public RemotePhase createRemotePhase() {
        return new RemotePhase();
    }

    /**
     * Create an instance of {@link RemoteDefect }
     * 
     */
    public RemoteDefect createRemoteDefect() {
        return new RemoteDefect();
    }

    /**
     * Create an instance of {@link RemoteRepositoryTree }
     * 
     */
    public RemoteRepositoryTree createRemoteRepositoryTree() {
        return new RemoteRepositoryTree();
    }

    /**
     * Create an instance of {@link RemoteOrganization }
     * 
     */
    public RemoteOrganization createRemoteOrganization() {
        return new RemoteOrganization();
    }

    /**
     * Create an instance of {@link RemoteNameValue }
     * 
     */
    public RemoteNameValue createRemoteNameValue() {
        return new RemoteNameValue();
    }

    /**
     * Create an instance of {@link Defect }
     * 
     */
    public Defect createDefect() {
        return new Defect();
    }

    /**
     * Create an instance of {@link RemoteRequirementTree }
     * 
     */
    public RemoteRequirementTree createRemoteRequirementTree() {
        return new RemoteRequirementTree();
    }

    /**
     * Create an instance of {@link RemoteData }
     * 
     */
    public RemoteData createRemoteData() {
        return new RemoteData();
    }

    /**
     * Create an instance of {@link RemoteRepositoryChild }
     * 
     */
    public RemoteRepositoryChild createRemoteRepositoryChild() {
        return new RemoteRepositoryChild();
    }

    /**
     * Create an instance of {@link RemoteCustomField }
     * 
     */
    public RemoteCustomField createRemoteCustomField() {
        return new RemoteCustomField();
    }

    /**
     * Create an instance of {@link RemoteReleaseTestSchedule }
     * 
     */
    public RemoteReleaseTestSchedule createRemoteReleaseTestSchedule() {
        return new RemoteReleaseTestSchedule();
    }

    /**
     * Create an instance of {@link RemoteRequirement }
     * 
     */
    public RemoteRequirement createRemoteRequirement() {
        return new RemoteRequirement();
    }

    /**
     * Create an instance of {@link RemoteRole }
     * 
     */
    public RemoteRole createRemoteRole() {
        return new RemoteRole();
    }

    /**
     * Create an instance of {@link RemoteCustomizableEntity.CustomProperties.Entry }
     * 
     */
    public RemoteCustomizableEntity.CustomProperties.Entry createRemoteCustomizableEntityCustomPropertiesEntry() {
        return new RemoteCustomizableEntity.CustomProperties.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcasesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcasesByCriteria")
    public JAXBElement<GetTestcasesByCriteria> createGetTestcasesByCriteria(GetTestcasesByCriteria value) {
        return new JAXBElement<GetTestcasesByCriteria>(_GetTestcasesByCriteria_QNAME, GetTestcasesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAttachmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateAttachmentResponse")
    public JAXBElement<UpdateAttachmentResponse> createUpdateAttachmentResponse(UpdateAttachmentResponse value) {
        return new JAXBElement<UpdateAttachmentResponse>(_UpdateAttachmentResponse_QNAME, UpdateAttachmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAttachments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "addAttachments")
    public JAXBElement<AddAttachments> createAddAttachments(AddAttachments value) {
        return new JAXBElement<AddAttachments>(_AddAttachments_QNAME, AddAttachments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcaseTreeByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcaseTreeByIdResponse")
    public JAXBElement<GetTestcaseTreeByIdResponse> createGetTestcaseTreeByIdResponse(GetTestcaseTreeByIdResponse value) {
        return new JAXBElement<GetTestcaseTreeByIdResponse>(_GetTestcaseTreeByIdResponse_QNAME, GetTestcaseTreeByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestCaseTreesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestCaseTreesByCriteriaResponse")
    public JAXBElement<GetTestCaseTreesByCriteriaResponse> createGetTestCaseTreesByCriteriaResponse(GetTestCaseTreesByCriteriaResponse value) {
        return new JAXBElement<GetTestCaseTreesByCriteriaResponse>(_GetTestCaseTreesByCriteriaResponse_QNAME, GetTestCaseTreesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCycleById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCycleById")
    public JAXBElement<GetCycleById> createGetCycleById(GetCycleById value) {
        return new JAXBElement<GetCycleById>(_GetCycleById_QNAME, GetCycleById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewRequirementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewRequirementResponse")
    public JAXBElement<CreateNewRequirementResponse> createCreateNewRequirementResponse(CreateNewRequirementResponse value) {
        return new JAXBElement<CreateNewRequirementResponse>(_CreateNewRequirementResponse_QNAME, CreateNewRequirementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAttachmentById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "deleteAttachmentById")
    public JAXBElement<DeleteAttachmentById> createDeleteAttachmentById(DeleteAttachmentById value) {
        return new JAXBElement<DeleteAttachmentById>(_DeleteAttachmentById_QNAME, DeleteAttachmentById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcaseById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcaseById")
    public JAXBElement<GetTestcaseById> createGetTestcaseById(GetTestcaseById value) {
        return new JAXBElement<GetTestcaseById>(_GetTestcaseById_QNAME, GetTestcaseById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomFields }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCustomFields")
    public JAXBElement<GetCustomFields> createGetCustomFields(GetCustomFields value) {
        return new JAXBElement<GetCustomFields>(_GetCustomFields_QNAME, GetCustomFields.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCycleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewCycleResponse")
    public JAXBElement<CreateNewCycleResponse> createCreateNewCycleResponse(CreateNewCycleResponse value) {
        return new JAXBElement<CreateNewCycleResponse>(_CreateNewCycleResponse_QNAME, CreateNewCycleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAttachment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateAttachment")
    public JAXBElement<UpdateAttachment> createUpdateAttachment(UpdateAttachment value) {
        return new JAXBElement<UpdateAttachment>(_UpdateAttachment_QNAME, UpdateAttachment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteRelease }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteRelease")
    public JAXBElement<RemoteRelease> createRemoteRelease(RemoteRelease value) {
        return new JAXBElement<RemoteRelease>(_RemoteRelease_QNAME, RemoteRelease.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementTreesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementTreesByCriteria")
    public JAXBElement<GetRequirementTreesByCriteria> createGetRequirementTreesByCriteria(GetRequirementTreesByCriteria value) {
        return new JAXBElement<GetRequirementTreesByCriteria>(_GetRequirementTreesByCriteria_QNAME, GetRequirementTreesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignTestSchedulesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "assignTestSchedulesResponse")
    public JAXBElement<AssignTestSchedulesResponse> createAssignTestSchedulesResponse(AssignTestSchedulesResponse value) {
        return new JAXBElement<AssignTestSchedulesResponse>(_AssignTestSchedulesResponse_QNAME, AssignTestSchedulesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementsByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementsByCriteria")
    public JAXBElement<GetRequirementsByCriteria> createGetRequirementsByCriteria(GetRequirementsByCriteria value) {
        return new JAXBElement<GetRequirementsByCriteria>(_GetRequirementsByCriteria_QNAME, GetRequirementsByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getUsersByCriteriaResponse")
    public JAXBElement<GetUsersByCriteriaResponse> createGetUsersByCriteriaResponse(GetUsersByCriteriaResponse value) {
        return new JAXBElement<GetUsersByCriteriaResponse>(_GetUsersByCriteriaResponse_QNAME, GetUsersByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewRequirementTreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewRequirementTreeResponse")
    public JAXBElement<CreateNewRequirementTreeResponse> createCreateNewRequirementTreeResponse(CreateNewRequirementTreeResponse value) {
        return new JAXBElement<CreateNewRequirementTreeResponse>(_CreateNewRequirementTreeResponse_QNAME, CreateNewRequirementTreeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementTreeByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementTreeByIdResponse")
    public JAXBElement<GetRequirementTreeByIdResponse> createGetRequirementTreeByIdResponse(GetRequirementTreeByIdResponse value) {
        return new JAXBElement<GetRequirementTreeByIdResponse>(_GetRequirementTreeByIdResponse_QNAME, GetRequirementTreeByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignTestSchedules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "assignTestSchedules")
    public JAXBElement<AssignTestSchedules> createAssignTestSchedules(AssignTestSchedules value) {
        return new JAXBElement<AssignTestSchedules>(_AssignTestSchedules_QNAME, AssignTestSchedules.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementTreeById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementTreeById")
    public JAXBElement<GetRequirementTreeById> createGetRequirementTreeById(GetRequirementTreeById value) {
        return new JAXBElement<GetRequirementTreeById>(_GetRequirementTreeById_QNAME, GetRequirementTreeById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteUser")
    public JAXBElement<RemoteUser> createRemoteUser(RemoteUser value) {
        return new JAXBElement<RemoteUser>(_RemoteUser_QNAME, RemoteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAttachmentsByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "deleteAttachmentsByCriteria")
    public JAXBElement<DeleteAttachmentsByCriteria> createDeleteAttachmentsByCriteria(DeleteAttachmentsByCriteria value) {
        return new JAXBElement<DeleteAttachmentsByCriteria>(_DeleteAttachmentsByCriteria_QNAME, DeleteAttachmentsByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTestcaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewTestcaseResponse")
    public JAXBElement<CreateNewTestcaseResponse> createCreateNewTestcaseResponse(CreateNewTestcaseResponse value) {
        return new JAXBElement<CreateNewTestcaseResponse>(_CreateNewTestcaseResponse_QNAME, CreateNewTestcaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestSchedulesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestSchedulesByCriteriaResponse")
    public JAXBElement<GetTestSchedulesByCriteriaResponse> createGetTestSchedulesByCriteriaResponse(GetTestSchedulesByCriteriaResponse value) {
        return new JAXBElement<GetTestSchedulesByCriteriaResponse>(_GetTestSchedulesByCriteriaResponse_QNAME, GetTestSchedulesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTestcaseTreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewTestcaseTreeResponse")
    public JAXBElement<CreateNewTestcaseTreeResponse> createCreateNewTestcaseTreeResponse(CreateNewTestcaseTreeResponse value) {
        return new JAXBElement<CreateNewTestcaseTreeResponse>(_CreateNewTestcaseTreeResponse_QNAME, CreateNewTestcaseTreeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteTestResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteTestResult")
    public JAXBElement<RemoteTestResult> createRemoteTestResult(RemoteTestResult value) {
        return new JAXBElement<RemoteTestResult>(_RemoteTestResult_QNAME, RemoteTestResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteProject")
    public JAXBElement<RemoteProject> createRemoteProject(RemoteProject value) {
        return new JAXBElement<RemoteProject>(_RemoteProject_QNAME, RemoteProject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewRequirement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewRequirement")
    public JAXBElement<CreateNewRequirement> createCreateNewRequirement(CreateNewRequirement value) {
        return new JAXBElement<CreateNewRequirement>(_CreateNewRequirement_QNAME, CreateNewRequirement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Member }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteProjectTeam")
    public JAXBElement<Member> createRemoteProjectTeam(Member value) {
        return new JAXBElement<Member>(_RemoteProjectTeam_QNAME, Member.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementsByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementsByCriteriaResponse")
    public JAXBElement<GetRequirementsByCriteriaResponse> createGetRequirementsByCriteriaResponse(GetRequirementsByCriteriaResponse value) {
        return new JAXBElement<GetRequirementsByCriteriaResponse>(_GetRequirementsByCriteriaResponse_QNAME, GetRequirementsByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteAttachment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteAttachment")
    public JAXBElement<RemoteAttachment> createRemoteAttachment(RemoteAttachment value) {
        return new JAXBElement<RemoteAttachment>(_RemoteAttachment_QNAME, RemoteAttachment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttachmentByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getAttachmentByIdResponse")
    public JAXBElement<GetAttachmentByIdResponse> createGetAttachmentByIdResponse(GetAttachmentByIdResponse value) {
        return new JAXBElement<GetAttachmentByIdResponse>(_GetAttachmentByIdResponse_QNAME, GetAttachmentByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCycle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewCycle")
    public JAXBElement<CreateNewCycle> createCreateNewCycle(CreateNewCycle value) {
        return new JAXBElement<CreateNewCycle>(_CreateNewCycle_QNAME, CreateNewCycle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestSchedulesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestSchedulesByCriteria")
    public JAXBElement<GetTestSchedulesByCriteria> createGetTestSchedulesByCriteria(GetTestSchedulesByCriteria value) {
        return new JAXBElement<GetTestSchedulesByCriteria>(_GetTestSchedulesByCriteria_QNAME, GetTestSchedulesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReleaseByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getReleaseByIdResponse")
    public JAXBElement<GetReleaseByIdResponse> createGetReleaseByIdResponse(GetReleaseByIdResponse value) {
        return new JAXBElement<GetReleaseByIdResponse>(_GetReleaseByIdResponse_QNAME, GetReleaseByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProjectByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getProjectByIdResponse")
    public JAXBElement<GetProjectByIdResponse> createGetProjectByIdResponse(GetProjectByIdResponse value) {
        return new JAXBElement<GetProjectByIdResponse>(_GetProjectByIdResponse_QNAME, GetProjectByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPhaseToCycle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "addPhaseToCycle")
    public JAXBElement<AddPhaseToCycle> createAddPhaseToCycle(AddPhaseToCycle value) {
        return new JAXBElement<AddPhaseToCycle>(_AddPhaseToCycle_QNAME, AddPhaseToCycle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProjectsByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getProjectsByCriteria")
    public JAXBElement<GetProjectsByCriteria> createGetProjectsByCriteria(GetProjectsByCriteria value) {
        return new JAXBElement<GetProjectsByCriteria>(_GetProjectsByCriteria_QNAME, GetProjectsByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcaseTreeById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcaseTreeById")
    public JAXBElement<GetTestcaseTreeById> createGetTestcaseTreeById(GetTestcaseTreeById value) {
        return new JAXBElement<GetTestcaseTreeById>(_GetTestcaseTreeById_QNAME, GetTestcaseTreeById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteCriteria")
    public JAXBElement<RemoteCriteria> createRemoteCriteria(RemoteCriteria value) {
        return new JAXBElement<RemoteCriteria>(_RemoteCriteria_QNAME, RemoteCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReleasesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getReleasesByCriteria")
    public JAXBElement<GetReleasesByCriteria> createGetReleasesByCriteria(GetReleasesByCriteria value) {
        return new JAXBElement<GetReleasesByCriteria>(_GetReleasesByCriteria_QNAME, GetReleasesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcaseByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcaseByIdResponse")
    public JAXBElement<GetTestcaseByIdResponse> createGetTestcaseByIdResponse(GetTestcaseByIdResponse value) {
        return new JAXBElement<GetTestcaseByIdResponse>(_GetTestcaseByIdResponse_QNAME, GetTestcaseByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttachmentsByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getAttachmentsByCriteria")
    public JAXBElement<GetAttachmentsByCriteria> createGetAttachmentsByCriteria(GetAttachmentsByCriteria value) {
        return new JAXBElement<GetAttachmentsByCriteria>(_GetAttachmentsByCriteria_QNAME, GetAttachmentsByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReleaseById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getReleaseById")
    public JAXBElement<GetReleaseById> createGetReleaseById(GetReleaseById value) {
        return new JAXBElement<GetReleaseById>(_GetReleaseById_QNAME, GetReleaseById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRequirementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateRequirementResponse")
    public JAXBElement<UpdateRequirementResponse> createUpdateRequirementResponse(UpdateRequirementResponse value) {
        return new JAXBElement<UpdateRequirementResponse>(_UpdateRequirementResponse_QNAME, UpdateRequirementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getUsersByCriteria")
    public JAXBElement<GetUsersByCriteria> createGetUsersByCriteria(GetUsersByCriteria value) {
        return new JAXBElement<GetUsersByCriteria>(_GetUsersByCriteria_QNAME, GetUsersByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPhaseToCycleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "addPhaseToCycleResponse")
    public JAXBElement<AddPhaseToCycleResponse> createAddPhaseToCycleResponse(AddPhaseToCycleResponse value) {
        return new JAXBElement<AddPhaseToCycleResponse>(_AddPhaseToCycleResponse_QNAME, AddPhaseToCycleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteRepositoryTreeTestcase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteRepositoryTreeTestcase")
    public JAXBElement<RemoteRepositoryTreeTestcase> createRemoteRepositoryTreeTestcase(RemoteRepositoryTreeTestcase value) {
        return new JAXBElement<RemoteRepositoryTreeTestcase>(_RemoteRepositoryTreeTestcase_QNAME, RemoteRepositoryTreeTestcase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTestStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateTestStatus")
    public JAXBElement<UpdateTestStatus> createUpdateTestStatus(UpdateTestStatus value) {
        return new JAXBElement<UpdateTestStatus>(_UpdateTestStatus_QNAME, UpdateTestStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteDepartment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteDepartment")
    public JAXBElement<RemoteDepartment> createRemoteDepartment(RemoteDepartment value) {
        return new JAXBElement<RemoteDepartment>(_RemoteDepartment_QNAME, RemoteDepartment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomFieldsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCustomFieldsResponse")
    public JAXBElement<GetCustomFieldsResponse> createGetCustomFieldsResponse(GetCustomFieldsResponse value) {
        return new JAXBElement<GetCustomFieldsResponse>(_GetCustomFieldsResponse_QNAME, GetCustomFieldsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAttachmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "addAttachmentsResponse")
    public JAXBElement<AddAttachmentsResponse> createAddAttachmentsResponse(AddAttachmentsResponse value) {
        return new JAXBElement<AddAttachmentsResponse>(_AddAttachmentsResponse_QNAME, AddAttachmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCycleByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCycleByIdResponse")
    public JAXBElement<GetCycleByIdResponse> createGetCycleByIdResponse(GetCycleByIdResponse value) {
        return new JAXBElement<GetCycleByIdResponse>(_GetCycleByIdResponse_QNAME, GetCycleByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReleasesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getReleasesByCriteriaResponse")
    public JAXBElement<GetReleasesByCriteriaResponse> createGetReleasesByCriteriaResponse(GetReleasesByCriteriaResponse value) {
        return new JAXBElement<GetReleasesByCriteriaResponse>(_GetReleasesByCriteriaResponse_QNAME, GetReleasesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestCaseTreesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestCaseTreesByCriteria")
    public JAXBElement<GetTestCaseTreesByCriteria> createGetTestCaseTreesByCriteria(GetTestCaseTreesByCriteria value) {
        return new JAXBElement<GetTestCaseTreesByCriteria>(_GetTestCaseTreesByCriteria_QNAME, GetTestCaseTreesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRequirement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateRequirement")
    public JAXBElement<UpdateRequirement> createUpdateRequirement(UpdateRequirement value) {
        return new JAXBElement<UpdateRequirement>(_UpdateRequirement_QNAME, UpdateRequirement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTestcase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateTestcase")
    public JAXBElement<UpdateTestcase> createUpdateTestcase(UpdateTestcase value) {
        return new JAXBElement<UpdateTestcase>(_UpdateTestcase_QNAME, UpdateTestcase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoteFieldValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "remoteFieldValue")
    public JAXBElement<RemoteFieldValue> createRemoteFieldValue(RemoteFieldValue value) {
        return new JAXBElement<RemoteFieldValue>(_RemoteFieldValue_QNAME, RemoteFieldValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementByIdResponse")
    public JAXBElement<GetRequirementByIdResponse> createGetRequirementByIdResponse(GetRequirementByIdResponse value) {
        return new JAXBElement<GetRequirementByIdResponse>(_GetRequirementByIdResponse_QNAME, GetRequirementByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestcasesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestcasesByCriteriaResponse")
    public JAXBElement<GetTestcasesByCriteriaResponse> createGetTestcasesByCriteriaResponse(GetTestcasesByCriteriaResponse value) {
        return new JAXBElement<GetTestcasesByCriteriaResponse>(_GetTestcasesByCriteriaResponse_QNAME, GetTestcasesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCyclesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCyclesByCriteriaResponse")
    public JAXBElement<GetCyclesByCriteriaResponse> createGetCyclesByCriteriaResponse(GetCyclesByCriteriaResponse value) {
        return new JAXBElement<GetCyclesByCriteriaResponse>(_GetCyclesByCriteriaResponse_QNAME, GetCyclesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttachmentById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getAttachmentById")
    public JAXBElement<GetAttachmentById> createGetAttachmentById(GetAttachmentById value) {
        return new JAXBElement<GetAttachmentById>(_GetAttachmentById_QNAME, GetAttachmentById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewRequirementTree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewRequirementTree")
    public JAXBElement<CreateNewRequirementTree> createCreateNewRequirementTree(CreateNewRequirementTree value) {
        return new JAXBElement<CreateNewRequirementTree>(_CreateNewRequirementTree_QNAME, CreateNewRequirementTree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTestcaseTree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewTestcaseTree")
    public JAXBElement<CreateNewTestcaseTree> createCreateNewTestcaseTree(CreateNewTestcaseTree value) {
        return new JAXBElement<CreateNewTestcaseTree>(_CreateNewTestcaseTree_QNAME, CreateNewTestcaseTree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestSchedulesById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestSchedulesById")
    public JAXBElement<GetTestSchedulesById> createGetTestSchedulesById(GetTestSchedulesById value) {
        return new JAXBElement<GetTestSchedulesById>(_GetTestSchedulesById_QNAME, GetTestSchedulesById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTestStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateTestStatusResponse")
    public JAXBElement<UpdateTestStatusResponse> createUpdateTestStatusResponse(UpdateTestStatusResponse value) {
        return new JAXBElement<UpdateTestStatusResponse>(_UpdateTestStatusResponse_QNAME, UpdateTestStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementTreesByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementTreesByCriteriaResponse")
    public JAXBElement<GetRequirementTreesByCriteriaResponse> createGetRequirementTreesByCriteriaResponse(GetRequirementTreesByCriteriaResponse value) {
        return new JAXBElement<GetRequirementTreesByCriteriaResponse>(_GetRequirementTreesByCriteriaResponse_QNAME, GetRequirementTreesByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAttachmentsByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getAttachmentsByCriteriaResponse")
    public JAXBElement<GetAttachmentsByCriteriaResponse> createGetAttachmentsByCriteriaResponse(GetAttachmentsByCriteriaResponse value) {
        return new JAXBElement<GetAttachmentsByCriteriaResponse>(_GetAttachmentsByCriteriaResponse_QNAME, GetAttachmentsByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCyclesByCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getCyclesByCriteria")
    public JAXBElement<GetCyclesByCriteria> createGetCyclesByCriteria(GetCyclesByCriteria value) {
        return new JAXBElement<GetCyclesByCriteria>(_GetCyclesByCriteria_QNAME, GetCyclesByCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewTestcase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "createNewTestcase")
    public JAXBElement<CreateNewTestcase> createCreateNewTestcase(CreateNewTestcase value) {
        return new JAXBElement<CreateNewTestcase>(_CreateNewTestcase_QNAME, CreateNewTestcase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRequirementById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getRequirementById")
    public JAXBElement<GetRequirementById> createGetRequirementById(GetRequirementById value) {
        return new JAXBElement<GetRequirementById>(_GetRequirementById_QNAME, GetRequirementById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProjectById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getProjectById")
    public JAXBElement<GetProjectById> createGetProjectById(GetProjectById value) {
        return new JAXBElement<GetProjectById>(_GetProjectById_QNAME, GetProjectById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestSchedulesByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getTestSchedulesByIdResponse")
    public JAXBElement<GetTestSchedulesByIdResponse> createGetTestSchedulesByIdResponse(GetTestSchedulesByIdResponse value) {
        return new JAXBElement<GetTestSchedulesByIdResponse>(_GetTestSchedulesByIdResponse_QNAME, GetTestSchedulesByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAttachmentByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "deleteAttachmentByIdResponse")
    public JAXBElement<DeleteAttachmentByIdResponse> createDeleteAttachmentByIdResponse(DeleteAttachmentByIdResponse value) {
        return new JAXBElement<DeleteAttachmentByIdResponse>(_DeleteAttachmentByIdResponse_QNAME, DeleteAttachmentByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProjectsByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "getProjectsByCriteriaResponse")
    public JAXBElement<GetProjectsByCriteriaResponse> createGetProjectsByCriteriaResponse(GetProjectsByCriteriaResponse value) {
        return new JAXBElement<GetProjectsByCriteriaResponse>(_GetProjectsByCriteriaResponse_QNAME, GetProjectsByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTestcaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "updateTestcaseResponse")
    public JAXBElement<UpdateTestcaseResponse> createUpdateTestcaseResponse(UpdateTestcaseResponse value) {
        return new JAXBElement<UpdateTestcaseResponse>(_UpdateTestcaseResponse_QNAME, UpdateTestcaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAttachmentsByCriteriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "deleteAttachmentsByCriteriaResponse")
    public JAXBElement<DeleteAttachmentsByCriteriaResponse> createDeleteAttachmentsByCriteriaResponse(DeleteAttachmentsByCriteriaResponse value) {
        return new JAXBElement<DeleteAttachmentsByCriteriaResponse>(_DeleteAttachmentsByCriteriaResponse_QNAME, DeleteAttachmentsByCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.service.thed.com/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

}
