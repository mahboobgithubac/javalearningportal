<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Serialization and Deserialization in Java</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f9fbff;
      color: #333;
      padding: 20px;
    }
    h2 {
      color: #003366;
      margin-top: 30px;
    }
    pre {
      background-color: #eef6ff;
      padding: 12px;
      border-left: 4px solid #007acc;
      overflow-x: auto;
    }
    ul {
      background: #fffbe6;
      padding: 10px;
      border-left: 4px solid #f1c40f;
    }
    .highlight {
      color: #d35400;
      font-weight: bold;
    }
  </style>
</head>
<body>

<h1>📦 Serialization and Deserialization in Java</h1>

<h2>✅ What is Serialization?</h2>
<p>Serialization is the process of converting a Java object into a byte stream for storage or transmission.</p>

<h2>🔁 What is Deserialization?</h2>
<p>Deserialization is the process of converting a byte stream back into a Java object.</p>

<h2>🧪 Basic Serialization Example</h2>
<pre><code>class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}</code></pre>

---

<h2>📌 Scenario 1: <code>transient</code> Keyword</h2>
<p>Fields marked <code>transient</code> are skipped during serialization.</p>

<pre><code>class User implements Serializable {
    String username;
    transient String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}</code></pre>
<p>🔍 After deserialization, <code>password</code> will be <code>null</code>.</p>

---

<h2>📌 Scenario 2: <code>static</code> Fields</h2>
<p><code>static</code> fields are not part of object state and are not serialized.</p>

<pre><code>class Config implements Serializable {
    static String appName = "MyApp";
    int version = 1;
}</code></pre>
<p>📝 <code>appName</code> will not be serialized. It remains part of the class, not the object.</p>

---

<h2>📌 Scenario 3: Inheritance</h2>
<p>If superclass is not serializable, its state is not saved during serialization.</p>

<pre><code>class Person {
    String address;
    public Person(String address) {
        this.address = address;
    }
}

class Employee extends Person implements Serializable {
    String name;
    public Employee(String name, String address) {
        super(address);
        this.name = name;
    }
}</code></pre>
<p>🔍 <code>address</code> will not be serialized unless <code>Person</code> also implements <code>Serializable</code>.</p>

---

<h2>📌 Scenario 4: Custom Serialization with <code>writeObject</code> / <code>readObject</code></h2>
<p>Override default behavior for sensitive data or transformation.</p>

<pre><code>class Account implements Serializable {
    String username;
    transient String password;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject("ENCRYPTED_" + password); // custom logic
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        password = ((String) in.readObject()).replace("ENCRYPTED_", "");
    }
}</code></pre>

---

<h2>📌 Scenario 5: Object Graph Serialization</h2>
<p>When an object contains references to other objects, they must also be serializable.</p>

<pre><code>class Engine implements Serializable {
    int power;
    public Engine(int power) { this.power = power; }
}

class Car implements Serializable {
    String model;
    Engine engine;
    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }
}</code></pre>
<p>✅ Both <code>Car</code> and <code>Engine</code> must implement <code>Serializable</code>.</p>

---

<h2>🎯 Common Interview Questions</h2>
<ul>
  <li><span class="highlight">Q:</span> What is the role of <code>serialVersionUID</code>?</li>
  <li><span class="highlight">Q:</span> Can a static field be serialized?</li>
  <li><span class="highlight">Q:</span> What is the difference between transient and static?</li>
  <li><span class="highlight">Q:</span> What happens if a field’s class is not serializable?</li>
  <li><span class="highlight">Q:</span> How do you handle custom serialization logic?</li>
  <li><span class="highlight">Q:</span> What is the impact of not declaring serialVersionUID?</li>
</ul>

---

<h2>📌 Summary</h2>
<ul>
  <li><code>Serializable</code> is a marker interface — no methods to override</li>
  <li>Use <code>transient</code> to skip sensitive data</li>
  <li>Use <code>serialVersionUID</code> for version consistency</li>
  <li>Custom logic: override <code>writeObject()</code> and <code>readObject()</code></li>
</ul>

</body>
</html>
