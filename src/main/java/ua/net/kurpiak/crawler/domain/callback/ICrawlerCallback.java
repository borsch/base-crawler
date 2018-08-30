package ua.net.kurpiak.crawler.domain.callback;

public interface ICrawlerCallback<T> {

    T newInstance();

    void handle(T object);
}
