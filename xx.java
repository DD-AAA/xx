package xdpp.output;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import xdpp.Interface.Sender;
import xdpp.flowfile.EventManager;
import xdpp.flowfile.FlowFileEvent;
import xdpp.entity.JsonDataEntity;
import xdpp.Interface.HandlerAdapter;
import java.util.UUID;

public class JsonInHandler implements HandlerAdapter{
    @Autowired
    GraphLoader graphLoader;
    @Autowired
    EventManager eventManager;
    FlowFileEvent flowFileEvent;
    public Object support(Object obj){
        if(!(obj instanceof byte[])){
        flowFileEvent = new FlowFileEvent(UUID.randomUUID().toString(),"JsonInHandler",System.currentTimeMillis(),obj.toString(),false);
        eventManager.addDataEvent(flowFileEvent);
        return null;
        }
        return handle(obj);
    }
    public Object handle(Object data){
        String jsonStr = (String)data;
        JsonDataEntity targetClass = JSON.parseObject(jsonStr, JsonDataEntity.class);
        Sender sender = graphLoader.getSender(12);
        sender.init();
        sender.send(targetClass);
        flowFileEvent = new FlowFileEvent(UUID.randomUUID().toString(),"JsonInHandler",System.currentTimeMillis(),data.toString(),true);
        eventManager.addDataEvent(flowFileEvent);
        return targetClass;
    }
}
