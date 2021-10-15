 /********* A. BASIC QUERY *********/
 --1
SELECT * FROM  student ORDER BY id;
SELECT * FROM  student ORDER BY gender;
SELECT * FROM  student ORDER BY birthday ASC , scholarship DESC;
-- 2
SELECT * FROM subject WHERE name LIKE 'T%';
--3
SELECT * FROM student WHERE name LIKE '%i';
--4
SELECT * FROM faculty WHERE name LIKE '_n%';
--5
SELECT * FROM student WHERE name LIKE '%Th?%';
--6
SELECT * FROM student WHERE name BETWEEN 'A' and 'M' ORDER BY name;
--7
SELECT * FROM student WHERE scholarship > 100000 ORDER BY faculty_id DESC;
--8
SELECT * FROM student WHERE scholarship >= 150000 AND hometown = 'Hà N?i';
--9
SELECT * FROM student 
WHERE birthday 
BETWEEN TO_DATE('01/JAN/1991','DD/MM/YYYY') AND TO_DATE('05/JUN/1992', 'DD/MM/YYYY');
--10
SELECT * FROM student WHERE scholarship BETWEEN 80000 AND 150000;
--11
SELECT * FROM subject WHERE lesson_quantity > 30 AND lesson_quantity < 45;

/********* B. CALCULATION QUERY *********/
--1
SELECT id, gender, faculty_id,
CASE WHEN scholarship > 500000 THEN 'H?c b?ng cao' ELSE 'H?c b?n trung bình' END scholarship
FROM student
WHERE scholarship > 0;
--2
SELECT COUNT(*) AS TongSV FROM student;
--3
SELECT gender, COUNT(*) AS Quantity FROM student GROUP BY gender;
--4
SELECT faculty.name, COUNT(student.id) 
FROM faculty, student 
WHERE faculty.id = student.faculty_id
GROUP BY faculty.name;
--5
SELECT subject.name, COUNT(*) AS quantity
FROM subject, exam_management
WHERE subject.id = exam_management.subject_id
GROUP BY subject.name;
--6
SELECT student.name, COUNT(*) AS quantity
FROM student, exam_management
WHERE student.id = exam_management.student_id
GROUP BY student.name;
-- 7. T?ng s? h?c b?ng c?a m?i khoa
SELECT faculty.name, COUNT(student.scholarship) AS scholarshi_pnum
FROM faculty, student
WHERE faculty.id = student.faculty_id
GROUP BY faculty.name;
-- 8. Cho bi?t h?c b?ng cao nh?t c?a m?i khoa
SELECT faculty.name, MAX(student.scholarship) AS HIGHEST_AMOUNT
FROM faculty, student
WHERE faculty.id = student.faculty_id
GROUP BY faculty.name;
-- 9. Cho bi?t t?ng s? sinh viên nam và t?ng s? sinh viên n? c?a m?i khoa
SELECT faculty.name,student.gender, COUNT(*) AS nam
FROM faculty, student
WHERE student.faculty_id = faculty.id AND student.gender = 'Nam'
GROUP BY faculty.name, student.gender
UNION
SELECT faculty.name,student.gender, COUNT(*) AS nu
FROM faculty, student
WHERE student.faculty_id = faculty.id AND student.gender = 'N?'
GROUP BY faculty.name, student.gender;
-- 10. Cho bi?t s? l??ng sinh viên theo t?ng ?? tu?i
SELECT EXTRACT( YEAR FROM student.birthday) AS NamSinh, COUNT(*) AS SoLuong
FROM student
GROUP BY EXTRACT(YEAR FROM student.birthday);
-- 11. Cho bi?t nh?ng n?i nào có h?n 2 sinh viên ?ang theo h?c t?i tr??ng
SELECT student.hometown
FROM student
GROUP BY student.hometown HAVING COUNT(*) > 2;
-- 12. Cho bi?t nh?ng sinh viên thi l?i ít nh?t 2 l?n
SELECT student.name, subject.name AS subject, COUNT(exam_management.number_of_exam_taking) AS Times
FROM student, exam_management, subject
WHERE student.id = exam_management.student_id AND subject.id = exam_management.subject_id
GROUP BY student.name, subject.name
HAVING COUNT(exam_management.number_of_exam_taking) >= 2;
-- 13. Cho bi?t nh?ng sinh viên nam có ?i?m trung bình l?n 1 trên 7.0 
SELECT student.name, AVG(exam_management.mark) AS DTB
FROM student, exam_management
WHERE student.id = exam_management.student_id 
    AND student.gender = 'Nam'
    AND exam_management.number_of_exam_taking = 1
GROUP BY student.name
HAVING AVG(exam_management.mark) > 7.0;
-- 14. Cho bi?t danh sách các sinh viên r?t trên 2 môn ? l?n thi 1 (r?t môn là ?i?m thi c?a môn không quá 4 ?i?m)
SELECT s.name, COUNT(*) AS SoMonRot
FROM student s, exam_management ex
WHERE s.id = ex.student_id AND ex.mark <=4 AND ex.number_of_exam_taking = 1
GROUP BY s.name
HAVING COUNT(*) > 2;
-- 15. Cho bi?t danh sách nh?ng khoa có nhi?u h?n 2 sinh viên nam (ch?a c?n JOIN)
SELECT faculty.name, COUNT(*) so_nam_sinh
FROM faculty, student
WHERE student.faculty_id = faculty.id
GROUP BY faculty.name
HAVING COUNT(*) > 2;
-- 16. Cho bi?t nh?ng khoa có 2 sinh viên ??t h?c b?ng t? 200000 ??n 300000
SELECT faculty.name, COUNT(*) AS quantity
FROM student, faculty
WHERE student.faculty_id = faculty.id AND student.scholarship BETWEEN 200000 AND 300000
GROUP BY faculty.name
HAVING COUNT(*) = 2;
-- 17. Cho bi?t sinh viên nào có h?c b?ng cao nh?t
SELECT * 
FROM student
WHERE student.scholarship = (SELECT MAX(student.scholarship) FROM student);

/********* C. DATE/TIME QUERY *********/

-- 1. Sinh viên có n?i sinh ? Hà N?i và sinh vào tháng 02
SELECT * FROM student WHERE student.birthday LIKE '%-FEB-%';
-- 2. Sinh viên có tu?i l?n h?n 20
SELECT * FROM student WHERE 2021 - EXTRACT (YEAR FROM student.birthday) > 20;
-- 3. Sinh viên sinh vào mùa xuân n?m 1990
SELECT * 
FROM student 
WHERE EXTRACT (MONTH FROM student.birthday) IN (1,2,3) 
AND EXTRACT (YEAR FROM student.birthday) = 1990;



-------------------------------------------------------------------


/********* D. JOIN QUERY *********/

-- 1. Danh sách các sinh viên c?a khoa ANH V?N và khoa V?T LÝ
SELECT student.name, faculty.name AS Khoa
FROM student INNER JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.name IN ('Anh - V?n', 'V?t lý');
-- 2. Nh?ng sinh viên nam c?a khoa ANH V?N và khoa TIN H?C
SELECT student.name, faculty.name AS Khoa
FROM student INNER JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.name IN ('Anh - V?n', 'Tin h?c') AND student.gender = 'Nam';
-- 3. Cho bi?t sinh viên nào có ?i?m thi l?n 1 môn c? s? d? li?u cao nh?t
SELECT student.name, subject.name AS subject, ex.mark
FROM student INNER JOIN exam_management ex ON student.id = ex.student_id
INNER JOIN subject ON ex.subject_id = subject.id
WHERE ex.number_of_exam_taking = 1 
AND ex.mark = (SELECT MAX(ex.mark) 
                FROM exam_management ex 
                WHERE ex.subject_id = (SELECT subject.id
                                        FROM subject 
                                        WHERE subject.name = 'C? s? d? li?u'));
-- 4. Cho bi?t sinh viên khoa anh v?n có tu?i l?n nh?t.
SELECT student.name, faculty.name, student.birthday
FROM student INNER JOIN faculty ON student.faculty_id = faculty.id
WHERE faculty.name = 'Anh - V?n'
AND ;
-- 5. Cho bi?t khoa nào có ?ông sinh viên nh?t

-- 6. Cho bi?t khoa nào có ?ông n? nh?t

-- 7. Cho bi?t nh?ng sinh viên ??t ?i?m cao nh?t trong t?ng môn

-- 8. Cho bi?t nh?ng khoa không có sinh viên h?c

-- 9. Cho bi?t sinh viên ch?a thi môn c? s? d? li?u

-- 10. Cho bi?t sinh viên nào không thi l?n 1 mà có d? thi l?n 2



 