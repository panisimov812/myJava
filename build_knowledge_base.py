#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Скрипт собирает все файлы из пакета itmoLessons в один JSON
и встраивает его в HTML-страницу базы знаний.
Запуск: python build_knowledge_base.py
"""

import os
import json
import html
from pathlib import Path
from urllib.parse import quote

# Корень проекта (где лежит скрипт)
SCRIPT_DIR = Path(__file__).resolve().parent
PROJECT_ROOT = SCRIPT_DIR
SRC = PROJECT_ROOT / "src" / "main" / "java" / "itmoLessons"
INTERVIEW_SRC = PROJECT_ROOT / "src" / "main" / "java" / "interview"
OUTPUT_HTML = PROJECT_ROOT / "knowledge-base" / "index.html"
OUTPUT_LESSONS_HTML = PROJECT_ROOT / "knowledge-base" / "lessons.html"

# Краткое описание уроков (для карточки на странице "По урокам")
LESSON_DESCRIPTIONS = {
    "lesson2": "Основы Java, переменные, типы данных.",
    "lesson3": "Циклы, условия, домашние задания.",
    "lesson4Array": "Объявление, копирование, сортировка массивов. Arrays, foreach.",
    "lesson5StringBuilder": "String — immutable, String Pool, intern; методы (equals, substring, split, replace); StringBuilder, StringBuffer; compact strings.",
    "lesson6": "Классы, объекты, композиция. Примеры: книги, горы, приложение питания.",
    "lesson7": "Наследование, абстрактные классы, интерфейсы. Unit, BattleUnit, Knight.",
    "lesson9EqualsClone": "Переопределение equals(), clone(), сравнение объектов.",
    "lesson10PrincipiOOP": "Абстракция, инкапсуляция, наследование, полиморфизм. Принципы SOLID.",
    "lesson11Enum": "Перечисления (enum), LocalDate, LocalTime, LocalDateTime.",
    "lesson12Exception": "try-catch, checked/unchecked, иерархия исключений.",
    "lesson13Generic": "Дженерики, обёртки, автоупаковка, wildcards.",
    "lesson14Collections": "ArrayList, Set, List, коллекции и итерация.",
    "lesson15Map": "HashMap, LinkedHashMap, ключ-значение, entrySet.",
    "kursovaya": "Задача «Фитнес»: абонементы, зоны, валидация.",
    "interview": "Process vs Thread, потоки, ExecutorService, race condition.",
    "interviewCollections": "ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue, thread-safe коллекции.",
    "interviewCollectionsCore": "List, Set, Map, Queue — архитектура JCF, ArrayList, HashMap, Big-O.",
    "interviewSOLID": "SOLID: Single Responsibility, Open/Closed, Liskov, Interface Segregation, Dependency Inversion.",
}

# Названия уроков для отображения
LESSON_TITLES = {
    "lesson2": "Урок 2: Основы",
    "lesson3": "Урок 3: Домашние задания",
    "lesson4Array": "Урок 4: Массивы",
    "lesson5StringBuilder": "Урок 5: Java String и StringBuilder (Core + Internals)",
    "lesson6": "Урок 6: Объекты и классы",
    "lesson7": "Урок 7: Наследование и абстракция",
    "lesson9EqualsClone": "Урок 9: equals и clone",
    "lesson10PrincipiOOP": "Урок 10: Принципы ООП, SOLID",
    "lesson11Enum": "Урок 11: Enum и дата/время",
    "lesson12Exception": "Урок 12: Исключения",
    "lesson13Generic": "Урок 13: Дженерики",
    "lesson14Collections": "Урок 14: Коллекции",
    "lesson15Map": "Урок 15: Map (HashMap, LinkedHashMap)",
    "kursovaya": "Курсовая: Фитнес-клуб",
    "interview": "Interview: Process vs Thread",
    "interviewCollections": "Interview: Concurrent Collections",
    "interviewCollectionsCore": "Interview: Collections (Core)",
    "interviewSOLID": "Interview: SOLID",
}


def get_lesson_key(relative_path: str) -> str:
    """Из пути itmoLessons/lesson15Map/foo.java получаем lesson15Map."""
    parts = relative_path.replace("\\", "/").split("/")
    if len(parts) >= 1:
        return parts[0]
    return "other"


def get_lesson_title(lesson_key: str) -> str:
    return LESSON_TITLES.get(lesson_key, lesson_key)


def get_lesson_description(lesson_key: str) -> str:
    return LESSON_DESCRIPTIONS.get(lesson_key, "")


def collect_files():
    lessons = {}
    if not SRC.exists():
        print(f"Папка не найдена: {SRC}")
        return lessons

    for root, _, files in os.walk(SRC):
        root_path = Path(root)
        for name in sorted(files):
            if not (name.endswith(".java") or name.endswith(".txt")):
                continue
            try:
                rel = root_path.relative_to(SRC)
                relative_path = str(rel / name).replace("\\", "/")
                lesson_key = get_lesson_key(relative_path)
                if lesson_key not in lessons:
                    lessons[lesson_key] = {
                        "id": lesson_key,
                        "title": get_lesson_title(lesson_key),
                        "files": [],
                    }
                full_path = root_path / name
                content = full_path.read_text(encoding="utf-8", errors="replace")
                ext = "java" if name.endswith(".java") else "txt"
                lessons[lesson_key]["files"].append({
                    "path": relative_path,
                    "name": name,
                    "type": ext,
                    "content": content,
                })
            except Exception as e:
                print(f"Ошибка при чтении {root_path / name}: {e}")

    # Собираем также пакет interview (Process vs Thread и Concurrent Collections — отдельные вкладки)
    if INTERVIEW_SRC.exists():
        def interview_lesson_key(relative_path: str, name: str) -> str:
            """Concurrent Collections, Core Collections, SOLID — отдельные вкладки."""
            if "ConcurrentCollection" in name or name == "ConcurrentCollections.txt":
                return "interviewCollections"
            if "CollectionsFramework" in name:
                return "interviewCollectionsCore"
            if "SOLID" in name:
                return "interviewSOLID"
            return "interview"

        for root, _, files in os.walk(INTERVIEW_SRC):
            root_path = Path(root)
            for name in sorted(files):
                if not (name.endswith(".java") or name.endswith(".txt")):
                    continue
                try:
                    rel = root_path.relative_to(INTERVIEW_SRC)
                    relative_path = str(rel / name).replace("\\", "/")
                    lesson_key = interview_lesson_key(relative_path, name)
                    path_in_kb = lesson_key + "/" + relative_path
                    if lesson_key not in lessons:
                        lessons[lesson_key] = {
                            "id": lesson_key,
                            "title": get_lesson_title(lesson_key),
                            "files": [],
                        }
                    full_path = root_path / name
                    content = full_path.read_text(encoding="utf-8", errors="replace")
                    ext = "java" if name.endswith(".java") else "txt"
                    lessons[lesson_key]["files"].append({
                        "path": path_in_kb,
                        "name": name,
                        "type": ext,
                        "content": content,
                    })
                except Exception as e:
                    print(f"Ошибка при чтении {root_path / name}: {e}")

    # Сортируем уроки по ключу (..., kursovaya, interview, interviewCollections)
    order = list(LESSON_TITLES.keys())
    sorted_lessons = []
    for key in order:
        if key in lessons:
            sorted_lessons.append(lessons[key])
    for key in sorted(lessons.keys()):
        if key not in order:
            sorted_lessons.append(lessons[key])

    return {"lessons": sorted_lessons}


NAV_HTML = '''
    <nav class="top-nav">
        <a href="lessons.html" class="nav-link">По урокам</a>
        <a href="index.html" class="nav-link">Все файлы</a>
    </nav>
'''

LESSONS_PAGE_STYLES = """
    .top-nav { display: flex; gap: 0.5rem; padding: 0.75rem 1.5rem; background: var(--bg-card); border-bottom: 1px solid var(--border); }
    .top-nav .nav-link { color: var(--text-muted); text-decoration: none; font-size: 0.9rem; }
    .top-nav .nav-link:hover { color: var(--accent); }
    .top-nav .nav-link.active { color: var(--accent); font-weight: 500; }
    .lessons-page { max-width: 900px; margin: 0 auto; padding: 1.5rem 1.5rem 4rem; }
    .lesson-section { margin-bottom: 3rem; scroll-margin-top: 1rem; }
    .lesson-section h2 { font-family: 'Unbounded', sans-serif; font-size: 1.35rem; color: var(--accent); margin: 0 0 0.5rem; padding-bottom: 0.5rem; border-bottom: 1px solid var(--border); }
    .lesson-desc { color: var(--text-muted); font-size: 0.9rem; margin-bottom: 1.25rem; }
    .block-label { font-size: 0.75rem; font-weight: 600; text-transform: uppercase; letter-spacing: 0.05em; color: var(--text-muted); margin: 1.25rem 0 0.5rem; }
    .note-card { background: var(--bg-card); border: 1px solid var(--border); border-radius: 8px; padding: 1rem 1.25rem; margin-bottom: 1rem; }
    .note-card h3 { font-size: 0.9rem; margin: 0 0 0.5rem; color: var(--green); }
    .note-card .note-body { white-space: pre-wrap; font-size: 0.85rem; line-height: 1.6; margin: 0; }
    .example-card { background: var(--bg-card); border: 1px solid var(--border); border-radius: 8px; margin-bottom: 1rem; overflow: hidden; }
    .example-card summary { padding: 0.75rem 1.25rem; cursor: pointer; font-size: 0.9rem; color: var(--orange); list-style: none; display: flex; align-items: center; gap: 0.5rem; }
    .example-card summary::before { content: '▶'; font-size: 0.7rem; transition: transform 0.2s; }
    .example-card[open] summary::before { transform: rotate(90deg); }
    .example-card pre { margin: 0; padding: 1rem 1.25rem; font-size: 0.8rem; overflow-x: auto; border-top: 1px solid var(--border); max-height: 320px; overflow-y: auto; }
    .example-card .open-full { display: inline-block; margin-top: 0.5rem; font-size: 0.8rem; color: var(--accent); }
    .example-card .open-full:hover { text-decoration: underline; }
    .lesson-index { margin-bottom: 2rem; padding: 1rem 1.25rem; background: var(--bg-card); border-radius: 8px; border: 1px solid var(--border); }
    .index-links { display: flex; flex-wrap: wrap; gap: 0.5rem 1rem; margin-top: 0.5rem; }
    .index-links a { color: var(--text-muted); text-decoration: none; font-size: 0.85rem; }
    .index-links a:hover { color: var(--accent); }
"""

COLLECTIONS_CORE_STYLES = """
    .collections-core-content { font-size: 0.9rem; line-height: 1.65; }
    .collections-core-content h3 { font-family: 'Unbounded', sans-serif; font-size: 1.1rem; color: var(--accent); margin: 1.75rem 0 0.75rem; padding-bottom: 0.35rem; border-bottom: 1px solid var(--border); }
    .collections-core-content h3:first-child { margin-top: 0; }
    .collections-core-content h4 { font-size: 0.95rem; color: var(--green); margin: 1.25rem 0 0.5rem; }
    .collections-core-content p { margin: 0.5rem 0; }
    .collections-core-content pre.inline-code { background: var(--bg); border: 1px solid var(--border); border-radius: 6px; padding: 0.75rem 1rem; margin: 0.5rem 0; overflow-x: auto; font-size: 0.85rem; }
    .collections-core-content .code-caption { font-size: 0.8rem; color: var(--orange); margin-bottom: 0.25rem; }
    .collections-core-content table { width: 100%; border-collapse: collapse; margin: 0.75rem 0; font-size: 0.85rem; }
    .collections-core-content th, .collections-core-content td { border: 1px solid var(--border); padding: 0.5rem 0.75rem; text-align: left; }
    .collections-core-content th { background: var(--bg-card); color: var(--accent); }
    .collections-core-content .interview-q { background: var(--bg-card); border-left: 4px solid var(--accent); padding: 0.75rem 1rem; margin: 0.75rem 0; border-radius: 0 6px 6px 0; }
"""


def parse_collections_framework_to_html(content: str) -> str:
    out = []
    lines = content.splitlines()
    i, n = 0, len(lines)
    def esc(s): return html.escape(s)
    while i < n:
        line, stripped = lines[i], lines[i].strip()
        if stripped.startswith("=") and "=" in stripped:
            i += 1
            continue
        if stripped.startswith("## "):
            out.append(f'<h3>{esc(stripped[3:].strip())}</h3>')
            i += 1
            continue
        if stripped.startswith("---") and stripped.endswith("---"):
            sub = stripped.replace("-", "").strip()
            if sub:
                out.append(f'<h4>{esc(sub)}</h4>')
            i += 1
            continue
        if "|" in line and (stripped.startswith("|") or stripped.startswith("+")):
            rows = []
            while i < n and "|" in lines[i]:
                rl = lines[i]
                if rl.strip().startswith("+-"):
                    i += 1
                    continue
                cells = [c.strip() for c in rl.split("|") if c.strip() or "|" in rl]
                if cells:
                    rows.append(cells)
                i += 1
            if rows:
                out.append("<table><thead><tr>")
                for c in rows[0]:
                    out.append(f"<th>{esc(c)}</th>")
                out.append("</tr></thead><tbody>")
                for r in rows[1:]:
                    out.append("<tr>")
                    for c in r:
                        out.append(f"<td>{esc(c)}</td>")
                    out.append("</tr>")
                out.append("</tbody></table>")
            continue
        if stripped.startswith("💬"):
            ql = [stripped]
            i += 1
            while i < n and lines[i].strip() and not lines[i].strip().startswith("💬"):
                ql.append(lines[i].strip())
                i += 1
            out.append(f'<div class="interview-q">{esc(" ".join(ql))}</div>')
            continue
        if stripped.startswith("Пример:") or stripped == "Пример:":
            out.append(f'<p class="code-caption">{esc(stripped)}</p>')
            i += 1
            code_lines = []
            while i < n and lines[i].startswith("    ") and lines[i].strip():
                code_lines.append(lines[i].rstrip())
                i += 1
            if code_lines:
                code_text = "\n".join(code_lines)
                out.append(f'<pre class="inline-code">{esc(code_text)}</pre>')
            continue
        if stripped:
            p_lines = [stripped]
            i += 1
            while i < n and lines[i].strip() and not lines[i].strip().startswith("## ") and not (lines[i].strip().startswith("---") and lines[i].strip().endswith("---")) and "|" not in lines[i] and not lines[i].strip().startswith("💬") and not lines[i].strip().startswith("Пример:"):
                p_lines.append(lines[i].strip())
                i += 1
            text = " ".join(p_lines)
            if text.startswith("# Java Collections Framework"):
                continue
            out.append(f"<p>{esc(text)}</p>")
            continue
        i += 1
    return "\n".join(out)


def build_lessons_html(data: dict) -> str:
    parts = []
    for lesson in data["lessons"]:
        lid = html.escape(lesson["id"])
        title = html.escape(lesson["title"])
        desc = html.escape(LESSON_DESCRIPTIONS.get(lesson["id"], ""))
        txt_files = [f for f in lesson["files"] if f["type"] == "txt"]
        java_files = [f for f in lesson["files"] if f["type"] == "java"]
        parts.append(f'<section class="lesson-section" id="{lid}">')
        parts.append(f'<h2>{title}</h2>')
        if desc:
            parts.append(f'<p class="lesson-desc">{desc}</p>')
        if txt_files:
            parts.append('<p class="block-label">Основная информация</p>')
            for f in txt_files:
                name = html.escape(f["name"])
                content = f["content"]
                if (lesson["id"] == "interviewCollectionsCore" and f["name"] == "CollectionsFramework.txt") or (lesson["id"] == "lesson5StringBuilder" and f["name"] == "StringStringBuilderCore.txt") or (lesson["id"] == "interviewSOLID" and f["name"] == "SOLID.txt"):
                    parsed = parse_collections_framework_to_html(content)
                    parts.append(f'<div class="note-card collections-core-card"><div class="collections-core-content">{parsed}</div></div>')
                else:
                    content_escaped = html.escape(content)
                    parts.append(f'<div class="note-card"><h3>{name}</h3><pre class="note-body">{content_escaped}</pre></div>')
        if java_files:
            parts.append('<p class="block-label">Примеры кода</p>')
            for f in java_files:
                path_encoded = quote(f["path"], safe="")
                name = html.escape(f["name"])
                lines = f["content"].splitlines()
                preview = "\n".join(lines[:30])
                if len(lines) > 30:
                    preview += "\n// ..."
                preview_escaped = html.escape(preview)
                parts.append(f'<div class="example-card"><details><summary>{name}</summary><pre>{preview_escaped}</pre><a class="open-full" href="index.html?path={path_encoded}">Открыть полностью →</a></details></div>')
        parts.append("</section>")
    return "\n".join(parts)


def main():
    data = collect_files()
    if not data["lessons"]:
        print("Нет данных для базы знаний.")
        return

    json_str = json.dumps(data, ensure_ascii=False, indent=2)
    # Экранируем для вставки в HTML (чтобы </script> в коде не сломало)
    json_escaped = json_str.replace("</script>", "<\\/script>")

    html_content = """<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>База знаний — itmoLessons</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;500&family=Unbounded:wght@400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0d1117;
            --bg-card: #161b22;
            --border: #30363d;
            --text: #e6edf3;
            --text-muted: #8b949e;
            --accent: #58a6ff;
            --accent-dim: #388bfd66;
            --green: #3fb950;
            --orange: #d29922;
        }
        * { box-sizing: border-box; }
        body {
            margin: 0;
            font-family: 'JetBrains Mono', monospace;
            background: var(--bg);
            color: var(--text);
            min-height: 100vh;
            line-height: 1.6;
        }
        .layout {
            display: grid;
            grid-template-columns: 280px 1fr;
            min-height: 100vh;
        }
        @media (max-width: 768px) {
            .layout { grid-template-columns: 1fr; }
            .sidebar { position: fixed; left: -280px; z-index: 20; transition: left 0.2s; }
            .sidebar.open { left: 0; }
        }
        .sidebar {
            background: var(--bg-card);
            border-right: 1px solid var(--border);
            padding: 1rem 0;
            overflow-y: auto;
            max-height: 100vh;
        }
        .sidebar h2 {
            font-family: 'Unbounded', sans-serif;
            font-size: 0.95rem;
            font-weight: 600;
            margin: 0 1rem 0.75rem;
            color: var(--accent);
        }
        .search-wrap {
            padding: 0 1rem 1rem;
        }
        .search-wrap input {
            width: 100%;
            padding: 0.5rem 0.75rem;
            background: var(--bg);
            border: 1px solid var(--border);
            border-radius: 6px;
            color: var(--text);
            font-family: inherit;
            font-size: 0.9rem;
        }
        .search-wrap input::placeholder { color: var(--text-muted); }
        .search-wrap input:focus {
            outline: none;
            border-color: var(--accent);
            box-shadow: 0 0 0 2px var(--accent-dim);
        }
        .lesson-block {
            margin-bottom: 0.5rem;
        }
        .lesson-title {
            font-size: 0.8rem;
            font-weight: 600;
            color: var(--text-muted);
            padding: 0.35rem 1rem;
            cursor: pointer;
            border-left: 3px solid transparent;
        }
        .lesson-title:hover { background: var(--bg); color: var(--text); }
        .lesson-title.active { border-left-color: var(--accent); color: var(--accent); background: var(--bg); }
        .file-list { padding-left: 0.5rem; }
        .file-link {
            display: block;
            padding: 0.25rem 1rem 0.25rem 1.5rem;
            font-size: 0.8rem;
            color: var(--text-muted);
            text-decoration: none;
            cursor: pointer;
            border-radius: 4px;
        }
        .file-link:hover { background: var(--bg); color: var(--accent); }
        .file-link.active { color: var(--accent); background: var(--bg); }
        .main {
            padding: 1.5rem 2rem 3rem;
            overflow-y: auto;
            max-height: 100vh;
        }
        .main h1 {
            font-family: 'Unbounded', sans-serif;
            font-size: 1.5rem;
            margin: 0 0 0.5rem;
            color: var(--text);
        }
        .main .path { font-size: 0.8rem; color: var(--text-muted); margin-bottom: 1rem; }
        .content pre {
            background: var(--bg-card);
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 1rem;
            overflow-x: auto;
            font-size: 0.85rem;
            white-space: pre-wrap;
            word-break: break-word;
        }
        .content .text-content { white-space: pre-wrap; }
        .empty-state {
            color: var(--text-muted);
            text-align: center;
            padding: 3rem 2rem;
        }
        .badge { font-size: 0.7rem; padding: 0.15rem 0.4rem; border-radius: 4px; background: var(--border); color: var(--text-muted); }
        .badge.java { background: var(--orange); color: var(--bg); }
        .badge.txt { background: var(--green); color: var(--bg); }
        .result-highlight { background: var(--accent-dim); border-radius: 2px; }
        .menu-toggle { display: none; position: fixed; top: 0.5rem; left: 0.5rem; z-index: 30;
            padding: 0.5rem; background: var(--bg-card); border: 1px solid var(--border);
            border-radius: 6px; color: var(--text); cursor: pointer; font-size: 1.2rem; }
        .top-nav { display: flex; gap: 0.5rem; padding: 0.75rem 1.5rem; background: var(--bg-card); border-bottom: 1px solid var(--border); }
        .top-nav .nav-link { color: var(--text-muted); text-decoration: none; font-size: 0.9rem; }
        .top-nav .nav-link:hover { color: var(--accent); }
        .top-nav .nav-link.active { color: var(--accent); font-weight: 500; }
        @media (max-width: 768px) {
            .menu-toggle { display: block; }
            .main { padding-left: 3rem; }
        }
    </style>
</head>
<body>
    <nav class="top-nav">
        <a href="lessons.html" class="nav-link">По урокам</a>
        <a href="index.html" class="nav-link active">Все файлы</a>
    </nav>
    <button class="menu-toggle" id="menuToggle" aria-label="Меню">☰</button>
    <div class="layout">
        <aside class="sidebar">
            <h2>📚 itmoLessons</h2>
            <div class="search-wrap">
                <input type="text" id="search" placeholder="Поиск по базе знаний..." autocomplete="off">
            </div>
            <nav id="nav"></nav>
        </aside>
        <main class="main">
            <div id="content" class="empty-state">Выберите файл в боковой панели или воспользуйтесь поиском.</div>
        </main>
    </div>
    <script type="application/json" id="knowledge-base-data">""" + json_escaped + """</script>
    <script>
        const data = JSON.parse(document.getElementById('knowledge-base-data').textContent);
        const nav = document.getElementById('nav');
        const contentEl = document.getElementById('content');
        const searchInput = document.getElementById('search');

        function renderNav(filter) {
            const query = (filter || '').trim().toLowerCase();
            let html = '';
            data.lessons.forEach(lesson => {
                const files = query
                    ? lesson.files.filter(f => (
                        lesson.title.toLowerCase().includes(query) ||
                        f.name.toLowerCase().includes(query) ||
                        f.content.toLowerCase().includes(query)
                    ))
                    : lesson.files;
                if (files.length === 0) return;
                html += `<div class="lesson-block" data-lesson="${lesson.id}">
                    <div class="lesson-title">${escapeHtml(lesson.title)}</div>
                    <div class="file-list">`;
                files.forEach(f => {
                    const fullPath = f.path;
                    html += `<a class="file-link" href="#" data-path="${escapeAttr(fullPath)}" data-type="${f.type}">${escapeHtml(f.name)}</a>`;
                });
                html += `</div></div>`;
            });
            nav.innerHTML = html || '<div class="empty-state">Ничего не найдено</div>';
        }

        function escapeHtml(s) {
            const div = document.createElement('div');
            div.textContent = s;
            return div.innerHTML;
        }
        function escapeAttr(s) {
            return s.replace(/"/g, '&quot;');
        }

        function findFile(path) {
            const lessonId = path.split('/')[0];
            const lesson = data.lessons.find(l => l.id === lessonId);
            if (!lesson) return null;
            return lesson.files.find(f => f.path === path) || null;
        }

        function showContent(path, file, query) {
            if (!file) return;
            const pre = file.type === 'java';
            let text = file.content;
            const escapeForHtml = s => s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
            if (query && query.trim()) {
                const q = query.trim().replace(/[.*+?^${}()|[\\]\\\\]/g, '\\\\$&');
                const re = new RegExp(`(${q})`, 'gi');
                text = escapeForHtml(text).replace(re, '<span class="result-highlight">$1</span>');
            } else {
                text = escapeForHtml(text);
            }
            contentEl.innerHTML = `
                <h1>${escapeHtml(file.name)}</h1>
                <div class="path">${escapeHtml(path)}</div>
                <div class="content">${pre ? '<pre>' + text + '</pre>' : '<div class="text-content">' + text + '</div>'}</div>
            `;
            contentEl.classList.remove('empty-state');
            document.querySelectorAll('.file-link.active').forEach(el => el.classList.remove('active'));
            const link = document.querySelector(`.file-link[data-path="${escapeAttr(path)}"]`);
            if (link) link.classList.add('active');
        }

        nav.addEventListener('click', e => {
            const link = e.target.closest('.file-link');
            if (!link) return;
            e.preventDefault();
            const path = link.getAttribute('data-path');
            const file = findFile(path);
            if (file) showContent(path, file, searchInput.value.trim());
        });

        searchInput.addEventListener('input', () => renderNav(searchInput.value));
        searchInput.addEventListener('keydown', e => {
            if (e.key === 'Enter') {
                const first = nav.querySelector('.file-link');
                if (first) first.click();
            }
        });

        document.getElementById('menuToggle').addEventListener('click', () => {
            document.querySelector('.sidebar').classList.toggle('open');
        });
        document.querySelector('.main').addEventListener('click', () => {
            document.querySelector('.sidebar').classList.remove('open');
        });

        renderNav();
        const pathParam = new URLSearchParams(location.search).get('path');
        if (pathParam) {
            const file = findFile(pathParam);
            if (file) showContent(pathParam, file, '');
        }
    </script>
</body>
</html>"""

    OUTPUT_HTML.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT_HTML.write_text(html_content, encoding="utf-8")
    print(f"Готово: {OUTPUT_HTML}")

    lessons_body = build_lessons_html(data)
    index_links = "".join(
        f'<a href="#{html.escape(lesson["id"])}">{html.escape(lesson["title"])}</a>'
        for lesson in data["lessons"]
    )
    lessons_full_html = f"""<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>По урокам — itmoLessons</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;500&family=Unbounded:wght@400;600&display=swap" rel="stylesheet">
    <style>
        :root {{
            --bg: #0d1117;
            --bg-card: #161b22;
            --border: #30363d;
            --text: #e6edf3;
            --text-muted: #8b949e;
            --accent: #58a6ff;
            --accent-dim: #388bfd66;
            --green: #3fb950;
            --orange: #d29922;
        }}
        * {{ box-sizing: border-box; }}
        body {{ margin: 0; font-family: 'JetBrains Mono', monospace; background: var(--bg); color: var(--text); min-height: 100vh; line-height: 1.6; }}
        {LESSONS_PAGE_STYLES}
        {COLLECTIONS_CORE_STYLES}
    </style>
</head>
<body>
    <nav class="top-nav">
        <a href="lessons.html" class="nav-link active">По урокам</a>
        <a href="index.html" class="nav-link">Все файлы</a>
    </nav>
    <div class="lessons-page">
        <h1 style="font-family: 'Unbounded', sans-serif; font-size: 1.5rem; margin-bottom: 1rem;">Обзор по урокам</h1>
        <div class="lesson-index">
            <p class="block-label">Перейти к уроку</p>
            <div class="index-links">{index_links}</div>
        </div>
        {lessons_body}
    </div>
</body>
</html>"""
    OUTPUT_LESSONS_HTML.write_text(lessons_full_html, encoding="utf-8")
    print(f"Готово: {OUTPUT_LESSONS_HTML}")

    print(f"Уроков: {len(data['lessons'])}, файлов: {sum(len(l['files']) for l in data['lessons'])}")


if __name__ == "__main__":
    main()
