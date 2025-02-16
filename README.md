# SearchMovie

SearchMovie – это мобильное Android-приложение для поиска информации о фильмах, работающее через API IMDB. Оно позволяет находить фильмы, просматривать их описания, актерский состав и хранить историю поисков.

## 🚀 Технологический стек

- **Языки**: Kotlin
- **Android**: Android Studio, Android SDK, ADB, XML, ConstraintLayout, RelativeLayout, RecyclerView, Fragment, Activity
- **Архитектуры**: MVVM, Single Activity
- **Сетевое взаимодействие**: HTTP, REST API, Retrofit, Gson
- **Базы данных**: Room, SQLite
- **Асинхронность**: Coroutines, Flow
- **Инструменты**: Glide, Koin

## 🎬 Функциональность

### 🔎 Экран поиска фильмов
![Экран поиска](https://iili.io/2pLnHPt.png)
Пользователь вводит название фильма в поисковую строку и получает список результатов. При нажатии на фильм открывается экран с его постером.

### 🎥 Экран постера фильма
![Экран постера](https://iili.io/2pLn9VI.png)
<img src="https://iili.io/2pLn9VI.png" width="400">
На этом экране отображается постер фильма. Наверху есть вкладки, переключаясь между которыми можно перейти на экран с информацией о фильме.

### 📜 Экран информации о фильме
![Экран информации](https://iili.io/2pLndKX.png)
Здесь представлена основная информация о фильме: год выпуска, страна, жанр, режиссер, сценаристы и актерский состав. Также доступна кнопка **Show Cast**, при нажатии на которую открывается экран с подробностями о съемочной группе.

### 🎭 Экран информации о касте фильма
![Экран кастинга](https://iili.io/2pLn2ln.gif)
Включает список актеров, режиссеров и продюсеров, участвовавших в фильме.

### 👤 Поиск актера
![Экран поиска актера](https://iili.io/2pLnKHG.png)
Позволяет искать актеров по имени, но элементы списка не кликабельны.

### 📜 История поиска
![История поиска](https://iili.io/2pLnfRf.png)
Список ранее найденных фильмов. Данные хранятся локально в базе данных Room + SQLite.

### ℹ️ Экран информации о приложении
![О приложении](https://iili.io/2pLnqN4.png)
Отображает версию приложения и имя разработчика.

## 📌 Как использовать
1. Введите название фильма в поисковую строку.
2. Выберите фильм из списка, чтобы просмотреть подробности.
3. Переключайтесь между вкладками "Постер" и "О фильме" для получения дополнительной информации.
4. Нажмите **Show Cast**, чтобы увидеть список актеров и съемочной группы.
5. Используйте вкладки навигации внизу экрана для поиска актеров, просмотра истории поиска и информации о приложении.

---

**SearchMovie – удобный инструмент для киноманов, который помогает находить информацию о фильмах в несколько кликов!** 🎬🍿
