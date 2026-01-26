package io.github.loadup.testify.asserts.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 数据库行差异模型 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowDiff {
  /** YAML 中定义的期望行索引（从 0 开始） */
  private int index;

  /** 差异类型： MISSING - 数据库中找不到匹配的行 DIFF - 找到行了，但字段内容对比失败 */
  private String type;

  /** 错误概览描述 */
  private String message;

  /** 原始的期望行数据（用于定位或记录日志） */
  private Map<String, Object> expectedRow;

  /** 具体的字段差异明细 Key: 字段名, Value: 字段差异详情 */
  private Map<String, FieldDiff> fieldDiffs;

  /** 快速构造缺失行的静态方法 */
  public static RowDiff missing(int index, String message, Map<String, Object> expectedRow) {
    return new RowDiff(index, "MISSING", message, expectedRow, null);
  }

  /** 快速构造内容差异行的静态方法 */
  public static RowDiff diff(
      int index,
      String message,
      Map<String, Object> expectedRow,
      Map<String, FieldDiff> fieldDiffs) {
    return new RowDiff(index, "DIFF", message, expectedRow, fieldDiffs);
  }
}
