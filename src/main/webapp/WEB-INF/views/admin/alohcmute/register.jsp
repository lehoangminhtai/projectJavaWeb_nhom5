<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Register - ALOHCMUTE</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #1a1a1a;
            color: #fff;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        .background-pattern {
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: url('https://your-pattern-image-url.jpg') repeat;
            opacity: 0.1;
            z-index: -1;
        }

        .container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            background-color: #333;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            overflow: hidden;
        }

        .card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(45deg, #007bff, #6c757d);
            opacity: 0.8;
            z-index: -1;
        }

        .card-header {
            background-color: transparent;
            border-bottom: 1px solid #555;
            text-align: center;
            padding: 20px 0;
        }

        .form-group label {
            color: #fff;
        }

        .form-control {
            background-color: #444;
            color: #fff;
        }

        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .logo-container {
            text-align: center;
            margin-bottom: 20px;
        }

        .logo {
            font-size: 36px;
            font-weight: bold;
            color: #fff;
        }

        .logo-subtext {
            font-size: 14px;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="background-pattern"></div>
    <div class="container">
        <div class="col-4">
            <div class="card">
                <div class="logo-container">
                    <div class="logo">ALOHCMUTE</div>
                    <div class="logo-subtext">"connect, interact, thrive together"</div>
                </div>
                <div class="card-header">
                    <h2>Register</h2>
                </div>
                <div class="card-body">
                    <form action="/register" method="post">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="fullname">Full Name:</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="btn-container">
                            <button type="submit" class="btn btn-primary">Register</button>
                            <a href="/login" class="btn btn-secondary">Login</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
