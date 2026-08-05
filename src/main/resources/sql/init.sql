TRUNCATE TABLE order_detail restart identity CASCADE;
TRUNCATE TABLE orders restart identity CASCADE;
TRUNCATE TABLE customer restart identity CASCADE;
TRUNCATE TABLE payment_method restart identity CASCADE;
TRUNCATE TABLE order_status restart identity CASCADE;
TRUNCATE TABLE product_stock restart identity CASCADE;
TRUNCATE TABLE product restart identity CASCADE;
TRUNCATE TABLE product_category restart identity CASCADE;
TRUNCATE TABLE employee_account restart identity CASCADE;
TRUNCATE TABLE employee restart identity CASCADE;
TRUNCATE TABLE department restart identity CASCADE;

-- --------------------------------------------------
-- 1. 部署 (department)
-- --------------------------------------------------
INSERT INTO department (name) VALUES 
('開発部'),
('営業部'),
('総務部');

-- --------------------------------------------------
-- 2. 社員 (employee)
-- --------------------------------------------------
INSERT INTO employee (department_id, name, name_kana) VALUES 
(1, '山田 太郎', 'ヤマダ タロウ'),
(1, '佐藤 花子', 'サトウ ハナコ'),
(2, '鈴木 一郎', 'スズキ イチロウ');

-- --------------------------------------------------
-- 3. 社員アカウント (employee_account)
-- --------------------------------------------------
INSERT INTO employee_account (employee_id, name, password) VALUES 
(1, 'yamada.t', 'hashed_pass_yamada_123'),
-- (2, 'sato.h', 'hashed_pass_sato_456'),
(3, 'suzuki.i', 'hashed_pass_suzuki_789');

-- --------------------------------------------------
-- 4. 商品カテゴリ (product_category)
-- --------------------------------------------------
INSERT INTO product_category (name) VALUES 
('文房具'),
('PC・周辺機器'),
('家具');

-- --------------------------------------------------
-- 5. 商品 (product)
-- --------------------------------------------------
INSERT INTO product (product_category_id, name, price, image_url, delete_flag) VALUES 
(1, 'ボールペン(黒)', 150, 'https://example.com/images/pen.jpg', 0),
(1, 'ノート(A5)', 200, 'https://example.com/images/notebook.jpg', 0),
(2, 'ワイヤレスマウス', 2980, 'https://example.com/images/mouse.jpg', 0),
(2, 'メカニカルキーボード', 12800, 'https://example.com/images/keyboard.jpg', 0),
(3, 'オフィスチェア', 19800, 'https://example.com/images/chair.jpg', 0);

-- --------------------------------------------------
-- 6. 商品在庫 (product_stock)
-- --------------------------------------------------
INSERT INTO product_stock (product_id, quantity) VALUES 
(1, 500),
(2, 300),
(3, 50),
(4, 20),
(5, 10);

-- --------------------------------------------------
-- 7. 注文ステータス (order_status)
-- --------------------------------------------------
INSERT INTO order_status (name) VALUES 
('注文受付'),
('発送準備中'),
('発送済み'),
('キャンセル');

-- --------------------------------------------------
-- 8. 支払い方法 (payment_method)
-- --------------------------------------------------
INSERT INTO payment_method (name) VALUES 
('クレジットカード'),
('銀行振込'),
('コンビニ決済');

-- --------------------------------------------------
-- 9. 顧客 (customer)
-- --------------------------------------------------
INSERT INTO customer (name, name_kana, address1, address2, phone_number, mail_address, username, password) VALUES 
('田中 健太', 'タナカ ケンタ', '東京都千代田区1-1-1', '丸の内ビル10F', '090-1234-5678', 'tanaka@example.com', 'tanaka_k', 'hashed_pass_tanaka'),
('高橋 由美', 'タカハシ ユミ', '大阪府大阪市北区2-2-2', NULL, '080-9876-5432', 'takahashi@example.com', 'takahashi_y', 'hashed_pass_takahashi');

-- --------------------------------------------------
-- 10. 注文 (orders)
-- --------------------------------------------------
INSERT INTO orders (customer_id, order_status_id, payment_method_id, amount_total) VALUES 
(1, 3, 1, 3280),  -- 注文1: 田中様 (合計 3,280円)
(2, 1, 2, 19800); -- 注文2: 高橋様 (合計 19,800円)

-- --------------------------------------------------
-- 11. 注文明細 (order_detail)
-- --------------------------------------------------
INSERT INTO order_detail (order_id, product_id, customer_id, count) VALUES 
-- 注文1の内訳 (ボールペン 2本: 300円 + マウス 1個: 2,980円 = 3,280円)
(1, 1, 1, 2),
(1, 3, 1, 1),

-- 注文2の内訳 (オフィスチェア 1脚: 19,800円 = 19,800円)
(2, 5, 2, 1);