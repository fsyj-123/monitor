package site.fsyj.monitor.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    private String id;

    private String areaId;

    private String name;

    private String projectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id.equals(area.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaId, name, projectId);
    }
}
