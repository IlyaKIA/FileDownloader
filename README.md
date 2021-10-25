# Тестовое задание File Downloader

## Инструкция
> Учетные записи:  
> `user1` – администратор. Пароль `1`  
> `user2` – пользователь. Пароль `2`  

Для удобства в репозиторий добавлен запакованный `war` сервлет и папка `data` с тестовым содержимым.

## Задача  
Создать сервис по выгрузке файлов из произвольного каталога. Сервис 
должен предоставлять доступ к файлам на основании ролевой модели доступа. 
По умолчанию все пользователи могут скачивать только файлы с 
расширением txt, для получения доступа к другим расширениям пользователь 
должен авторизоваться. Доступа к функции настройки ролей должен быть у 
пользователя admin.  

Требуется реализовать данный модуль, используя технологию 
SERVLET, а также управление ролями и доступом через javax.servlet.Filter, 
javax.servlet.Listener. ВЕБ интерфейс простейший
