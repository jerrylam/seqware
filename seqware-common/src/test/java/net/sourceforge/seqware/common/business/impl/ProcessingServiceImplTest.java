package net.sourceforge.seqware.common.business.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import net.sourceforge.seqware.common.BaseUnit;
import net.sourceforge.seqware.common.business.ProcessingService;
import net.sourceforge.seqware.common.business.RegistrationService;
import net.sourceforge.seqware.common.factory.BeanFactory;
import net.sourceforge.seqware.common.hibernate.InSessionExecutions;
import net.sourceforge.seqware.common.model.File;
import net.sourceforge.seqware.common.model.Processing;
import net.sourceforge.seqware.common.model.RegistrationDTO;
import net.sourceforge.seqware.common.util.Log;

import org.junit.Test;

public class ProcessingServiceImplTest extends BaseUnit {

  public ProcessingServiceImplTest() throws Exception {
    super();
  }

  @Test
  public void testProcessingWithFiles() {
    Log.info("Processing without files");
    InSessionExecutions.bindSessionToThread();
    ProcessingService processingService = BeanFactory.getProcessingServiceBean();
    Processing processing = processingService.findByID(4);
    System.out.print(processing.getFiles());
    InSessionExecutions.unBindSessionFromTheThread();
  }

  @Test
  public void testProcessingWithoutFiles() {
    Log.info("Processing without files");
    InSessionExecutions.bindSessionToThread();
    ProcessingService processingService = BeanFactory.getProcessingServiceBean();
    Processing processing = processingService.findByID(4);
    System.out.print(processing.getFiles());
    InSessionExecutions.unBindSessionFromTheThread();
  }

  @Test
  public void testInsertProcessing() {
    ProcessingService ss = BeanFactory.getProcessingServiceBean();
    RegistrationService rs = BeanFactory.getRegistrationServiceBean();
    RegistrationDTO regDto = rs.findByEmailAddressAndPassword("admin@admin.com", "admin");

    Processing newProcessing = new Processing();
    newProcessing.setOwner(regDto);
    newProcessing.setFiles(new HashSet<File>());
    newProcessing.setStatus("success");
    newProcessing.setExitStatus(0);
    newProcessing.setProcessExitStatus(0);
    newProcessing.setRunStartTimestamp(null);
    newProcessing.setRunStopTimestamp(null);
    newProcessing.setAlgorithm("upload");
    newProcessing.setCreateTimestamp(new Date());
    ss.insert(newProcessing);
  }

  @Test
  public void testFindByCriteria() {
    ProcessingService processingService = BeanFactory.getProcessingServiceBean();
    // find using SWID number
    List<Processing> found = processingService.findByCriteria("14567", false);
    assertEquals(1, found.size());

    // Case sens
    found = processingService.findByCriteria("simple", true);
    assertEquals(10299, found.size());

    found = processingService.findByCriteria("SIMPLE", true);
    assertEquals(0, found.size());
  }
}
