package pojos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonPlaceHolderPojoHW12 {

    /*
{
  "id": 0,
  "name": "doggie",

  "status": "available"
}
*/
    private Integer  id;
    private String  name;
    private String  status;


    public JsonPlaceHolderPojoHW12()
    {}
    public JsonPlaceHolderPojoHW12(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPojoHW12{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
