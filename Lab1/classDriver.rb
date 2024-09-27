class Driver
  # Инкапсуляция полей
  attr_reader :id, :last_name, :first_name, :middle_name, :experience

  # Конструктор для инициализации объекта Driver
  def initialize(id, last_name, first_name, middle_name, experience)
    @id = validate_id(id) # валидация id
    self.last_name = last_name
    self.first_name = first_name
    self.middle_name = middle_name
    self.experience = experience
  end

  # Статический метод для создания объекта Driver с проверкой всех полей
  def self.create_driver(id, last_name, first_name, middle_name, experience)
    new(id, last_name, first_name, middle_name, experience)
  end

  # Валидации для полей
  def validate_id(id)
    raise 'ID должен быть положительным числом' unless id.is_a?(Integer) && id > 0
    id
  end

  def validate_name(name, field_name)
    raise "#{field_name} не может быть пустым" if name.nil? || name.strip.empty?
    name
  end

  def validate_experience(experience)
    raise 'Стаж должен быть положительным числом' unless experience.is_a?(Integer) && experience >= 0
    experience
  end

  # Сеттеры с валидацией
  def last_name=(last_name)
    @last_name = validate_name(last_name, "Фамилия")
  end

  def first_name=(first_name)
    @first_name = validate_name(first_name, "Имя")
  end

  def middle_name=(middle_name)
    @middle_name = validate_name(middle_name, "Отчество")
  end

  def experience=(experience)
    @experience = validate_experience(experience)
  end

  # Метод Информация о водителе
  def info
    "Водитель: #{last_name} #{first_name} #{middle_name}, Стаж: #{experience} лет"
  end
end

# Пример
begin
  driver = Driver.create_driver(1, "Иванов", "Иван", "Иванович", 10)
  puts driver.info
rescue => e
  puts "Ошибка: #{e.message}"
end

# Пример плохой:
# driver = Driver.create_driver(1, "", "Иван", "Иванович", 10)
