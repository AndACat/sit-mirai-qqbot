package org.sit.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sit.dto.ConstantRespDto;
import java.util.List;

/**
 * @author WangZhen
 * @Date 2023/12/14 0:29
 */
@Setter
@Getter
@ToString
public class ConstantRespVo {
    private List<ConstantRespDto> constantRespDtoList;
}
