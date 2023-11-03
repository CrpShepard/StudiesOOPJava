package redis.task.redis_task;

public interface MessagePublisher {
    void publish(final String message);
}
