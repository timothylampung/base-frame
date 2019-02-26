INSERT INTO public.act_ge_bytearray (id_, rev_, name_, deployment_id_, bytes_, generated_) VALUES ('2', 1, 'maintenance-request.bpmn20.xml', '1', '<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns:flowable="http://flowable.org/bpmn"
             xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
             typeLanguage="http://www.w3.org/2001/XMLSchema"
             expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="Fixed">

    <process id="maintenance_request_workflow" name="Maintenance Request Workflow" isExecutable="true">
        <startEvent id="start"/>
        <sequenceFlow id="flow1.1" sourceRef="start" targetRef="maintenanceRequest_draft_ST"/>
        <serviceTask id="maintenanceRequest_draft_ST" name="draft_ST"
                     flowable:expression="#{maintenanceRequest_draft_ST.execute(execution)}"/>
        <sequenceFlow id="flow2.1" sourceRef="maintenanceRequest_draft_ST"
                      targetRef="maintenanceRequest_draft_UT"/>
        <userTask id="maintenanceRequest_draft_UT"
                  name="com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest:DRAFTED"
                  flowable:candidateGroups="#{maintenanceRequestRouter.findDrafterCandidates(requestId)}">
        </userTask>
        <sequenceFlow id="flow2.3" sourceRef="maintenanceRequest_draft_UT"
                      targetRef="maintenanceRequest_complete_ST"/>

        <sequenceFlow sourceRef="maintenanceRequest_complete_ST" targetRef="end"
                      id="maintenanceRequest_complete_ST-end"/>
        <serviceTask id="maintenanceRequest_complete_ST" name="complete_ST"
                     flowable:expression="#{maintenanceRequest_complete_ST.execute(execution)}"/>
        <endEvent id="end"/>
    </process>
</definitions>', false);
INSERT INTO public.act_ge_bytearray (id_, rev_, name_, deployment_id_, bytes_, generated_) VALUES ('5', 1, 'work-order.bpmn20.xml', '4', '<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns:flowable="http://flowable.org/bpmn"
             xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
             typeLanguage="http://www.w3.org/2001/XMLSchema"
             expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="Fixed">
    <process id="work_order_workflow" name="Work Order Workflow" isExecutable="true">
        <startEvent id="start"/>
        <sequenceFlow id="flow1.1" sourceRef="start" targetRef="workOrder_draft_ST"/>
        <serviceTask id="workOrder_draft_ST" name="draft_ST"
                     flowable:expression="#{workOrder_draft_ST.execute(execution)}"/>
        <sequenceFlow id="flow2.1" sourceRef="workOrder_draft_ST"
                      targetRef="workOrder_draft_UT"/>
        <userTask id="workOrder_draft_UT"
                  name="com.assettagging.spotit.workorder.domain.model.DexWorkOrder:DRAFTED"
                  flowable:assignee="${userCreator}"
                  flowable:candidateGroups="#{workOrderRouter.findDrafterCandidates(orderId)}">
        </userTask>
        <sequenceFlow id="flow2.3" sourceRef="workOrder_draft_UT"
                      targetRef="workOrder_register_ST"/>
        <serviceTask id="workOrder_register_ST" name="register_ST"
                     flowable:expression="#{workOrder_register_ST.execute(execution)}"/>
        <sequenceFlow id="flow2.4" sourceRef="workOrder_register_ST"
                      targetRef="workOrder_register_UT"/>
        <userTask id="workOrder_register_UT"
                  name="com.assettagging.spotit.workorder.domain.model.DexWorkOrder:REGISTERED"
                  flowable:candidateGroups="#{workOrderRouter.findCheckerCandidates(orderId)}">
        </userTask>
        <sequenceFlow id="flow2.5" sourceRef="workOrder_register_UT"
                      targetRef="workOrder_check_ST"/>
        <serviceTask id="workOrder_check_ST" name="check_ST"
                     flowable:expression="#{workOrder_check_ST.execute(execution)}"/>
        <sequenceFlow id="flow2.6" sourceRef="workOrder_check_ST"
                      targetRef="workOrder_check_UT"/>
        <userTask id="workOrder_check_UT"
                  name="com.assettagging.spotit.workorder.domain.model.DexWorkOrder:CHECKED"
                  flowable:candidateGroups="#{workOrderRouter.findApproverCandidates(orderId)}">
        </userTask>
        <sequenceFlow id="flow2.7" sourceRef="workOrder_check_UT"
                      targetRef="workOrder_approve_ST"/>
        <serviceTask id="workOrder_approve_ST" name="approve_ST"
                     flowable:expression="#{workOrder_approve_ST.execute(execution)}"/>
        <sequenceFlow id="flow9.1" sourceRef="workOrder_approve_ST" targetRef="workOrder_complete_ST"/>
        <serviceTask id="workOrder_complete_ST" name="complete_ST"
                     flowable:expression="#{workOrder_complete_ST.execute(execution)}"/>
        <endEvent id="end"/>
    </process>
</definitions>', false);