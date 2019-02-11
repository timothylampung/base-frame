package my.spotit.asset.common.api.controller;

import my.spotit.asset.DexConstants;
import my.spotit.asset.common.api.vo.GradeCode;
import my.spotit.asset.common.api.vo.GradeCodeResult;
import my.spotit.asset.common.api.vo.PositionCode;
import my.spotit.asset.common.api.vo.PositionCodeResult;
import my.spotit.asset.common.domain.model.DexGradeCode;
import my.spotit.asset.common.domain.model.DexGradeCodeImpl;
import my.spotit.asset.common.domain.model.DexPositionCode;
import my.spotit.asset.common.domain.model.DexPositionCodeImpl;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.common.business.service.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@Transactional
@RestController
@RequestMapping("/api/common")
public class CommonController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    private CommonService commonService;
    private SystemService systemService;
    private CommonTransformer commonTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public CommonController(CommonService commonService, SystemService systemService,
                            CommonTransformer commonTransformer,
                            AuthenticationManager authenticationManager) {
        this.commonService = commonService;
        this.systemService = systemService;
        this.commonTransformer = commonTransformer;
        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // POSITION_CODE
    //==============================================================================================

    @GetMapping(value = "/position-codes", params = {"page"})
    public ResponseEntity<PositionCodeResult> findPagedPositionCodes(@RequestParam Integer page) {
        LOG.debug("findPagedPositionCodes: {}", page);
        Integer count = commonService.countPositionCode("%");
        List<PositionCode> positionCodes = commonTransformer.toPositionCodeVos(
                commonService.findPositionCodes("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<PositionCodeResult>(new PositionCodeResult(positionCodes, count), HttpStatus.OK);
    }

    @GetMapping(value = "/position-codes")
    public ResponseEntity<List<PositionCode>> findPositionCodes() {
        return new ResponseEntity<List<PositionCode>>(commonTransformer.toPositionCodeVos(
                commonService.findPositionCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/position-code/{code}")
    public ResponseEntity<PositionCode> findPositionCodeByCode(@PathVariable String code) {
        return new ResponseEntity<PositionCode>(commonTransformer.toPositionCodeVo(
                commonService.findPositionCodeByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/position-code")
    public ResponseEntity<String> savePositionCode(@RequestBody PositionCode vo) {
        DexPositionCode positionCode = new DexPositionCodeImpl();
        positionCode.setCode(vo.getCode());
        positionCode.setDescription(vo.getDescription());
        commonService.savePositionCode(positionCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/position-code/{code}")
    public ResponseEntity<String> updatePositionCode(@PathVariable String code, @RequestBody PositionCode vo) {
        DexPositionCode positionCode = commonService.findPositionCodeById(vo.getId());
        positionCode.setDescription(vo.getDescription());
        commonService.updatePositionCode(positionCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/position-code/{code}")
    public ResponseEntity<String> removePositionCode(@PathVariable String code) {
        DexPositionCode positionCode = commonService.findPositionCodeByCode(code);
        commonService.removePositionCode(positionCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //==============================================================================================
    // GRADE_CODE
    //==============================================================================================

    @GetMapping(value = "/grade-codes", params = {"page"})
    public ResponseEntity<GradeCodeResult> findPagedGradeCodes(@RequestParam Integer page) {
        LOG.debug("findPagedGradeCodes: {}", page);
        Integer count = commonService.countGradeCode("%");
        List<GradeCode> gradeCodes = commonTransformer.toGradeCodeVos(
                commonService.findGradeCodes("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<GradeCodeResult>(new GradeCodeResult(gradeCodes, count), HttpStatus.OK);
    }

    @GetMapping(value = "/grade-codes")
    public ResponseEntity<List<GradeCode>> findGradeCodes() {
        return new ResponseEntity<List<GradeCode>>(commonTransformer.toGradeCodeVos(
                commonService.findGradeCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/grade-code/{code}")
    public ResponseEntity<GradeCode> findGradeCodeByCode(@PathVariable String code) {
        return new ResponseEntity<GradeCode>(commonTransformer.toGradeCodeVo(
                commonService.findGradeCodeByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/grade-code")
    public ResponseEntity<String> saveGradeCode(@RequestBody GradeCode vo) {
        DexGradeCode gradeCode = new DexGradeCodeImpl();
        gradeCode.setCode(vo.getCode());
        gradeCode.setDescription(vo.getDescription());
        commonService.saveGradeCode(gradeCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/grade-code/{code}")
    public ResponseEntity<String> updateGradeCode(@PathVariable String code, @RequestBody GradeCode vo) {
        DexGradeCode gradeCode = commonService.findGradeCodeById(vo.getId());
        gradeCode.setDescription(vo.getDescription());
        commonService.updateGradeCode(gradeCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/grade-code/{code}")
    public ResponseEntity<String> removeGradeCode(@PathVariable String code) {
        DexGradeCode gradeCode = commonService.findGradeCodeByCode(code);
        commonService.removeGradeCode(gradeCode);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // PRIVATE METHODS
    // =============================================================================================
}
