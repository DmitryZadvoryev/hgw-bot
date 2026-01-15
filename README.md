# HGW Telegram Bot

## 📝 Описание

Telegram-бот, разработанный на Java с использованием Telegram Bot API, PostgreSQL и Hibernate ORM. Проект демонстрирует интеграцию современных технологий для создания масштабируемого бота с персистентным хранением данных.

## 🚀 Технологии

- **Java 8** - основной язык программирования
- **Maven** - управление зависимостями и сборка проекта
- **Telegram Bots API 4.3.1** - взаимодействие с Telegram
- **PostgreSQL 42.2.6** - реляционная база данных
- **Hibernate 5.4.3** - ORM для работы с базой данных

## 📋 Требования

- JDK 8 или выше
- Maven 3.x
- PostgreSQL 9.x или выше
- Telegram Bot Token (получить у [@BotFather](https://t.me/botfather))

## ⚙️ Установка и настройка

### 1. Клонирование репозитория

```bash
git clone https://github.com/DmitryZadvoryev/hgw-bot.git
cd hgw-bot
```

### 2. Настройка базы данных

Создайте базу данных PostgreSQL:

```sql
CREATE DATABASE hgw_bot;
CREATE USER bot_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE hgw_bot TO bot_user;
```

### 3. Настройка конфигурации

Создайте файл конфигурации с настройками подключения к БД и Telegram токеном.

### 4. Сборка проекта

```bash
mvn clean install
```

### 5. Запуск

```bash
mvn exec:java
```

## 🏗️ Структура проекта

```
hgw-bot/
├── src/
│   └── main/
│       └── java/
│           └── [исходный код]
├── pom.xml
├── .gitignore
└── README.md
```

## 🔧 Основные возможности

- Обработка команд пользователей
- Сохранение данных в PostgreSQL через Hibernate
- Асинхронная обработка сообщений
- ORM-маппинг для работы с БД

## 📦 Зависимости

- `org.telegram:telegrambots:4.3.1` - Telegram Bot API
- `org.postgresql:postgresql:42.2.6` - PostgreSQL JDBC драйвер
- `org.hibernate:hibernate-agroal:5.4.3.Final` - Hibernate ORM

## 🤝 Контрибуция

Приветствуются pull requests. Для серьёзных изменений, пожалуйста, сначала откройте issue для обсуждения.

## 📄 Лицензия

Проект создан в образовательных целях.

## 👤 Автор

**Dmitry Zadvoryev**
- GitHub: [@DmitryZadvoryev](https://github.com/DmitryZadvoryev)
- Profile: QA Automation Engineer

---

⭐ Если проект оказался полезным, поставьте звезду!
