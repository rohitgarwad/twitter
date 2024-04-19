import React from "react";
import { Grid, TextField, Button, InputLabel, Select, MenuItem, InputAdornment, IconButton} from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { useFormik } from "formik";
import { useDispatch } from "react-redux";
import * as Yup from "yup";
import { registerUser } from "../../Store/Auth/Action";

const validationSchema = Yup.object().shape({
  fullName: Yup.string()
    .min(1, 'Too Short!')
    .max(50, 'Too Long!')
    .required('Required'),
  email: Yup.string()
    .email("Invalid email")
    .required("Email is Required"),
  password: Yup.string()
    .min(8, 'Too Weak')
    .required("Password is Required"),
});

const currentYear = new Date().getFullYear();
const years = Array.from({ length: 100 }, (_, i) => currentYear - i);
const days = Array.from({ length: 31 }, (_, i) => i + 1);
const months = [
  { value: 1, label: "January" },
  { value: 2, label: "February" },
  { value: 3, label: "March" },
  { value: 4, label: "April" },
  { value: 5, label: "May" },
  { value: 6, label: "June" },
  { value: 7, label: "July" },
  { value: 8, label: "August" },
  { value: 9, label: "September" },
  { value: 10, label: "October" },
  { value: 11, label: "November" },
  { value: 12, label: "December" },
];

const SignupForm = () => {

  const [showPassword, setShowPassword] = React.useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const dispatch = useDispatch();

  const formik = useFormik({
    initialValues: {
      fullName: "",
      email: "",
      password: "",
      dateOfBirth: {
        day: "",
        month: "",
        year: "",
      },
    },
    validationSchema,
    onSubmit: (values) => {
      const { day, month, year } = values.dateOfBirth;
      const dateOfBirth = `${year}-${month}-${day}`;
      values.dateOfBirth = dateOfBirth;
      dispatch(registerUser(values));
      console.log("form value ", values);
    },
  });

  const handleDateChange = (name) => (event) => {
    formik.setFieldValue("dateOfBirth", {
      ...formik.values.dateOfBirth,
      [name]: event.target.value,
    });
  };

  return (
    <form onSubmit={formik.handleSubmit}>
      <Grid container spacing={2}>
{/* FullName */}
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Full Name"
            name="fullName"
            variant="outlined"
            size="large"
            value={formik.values.fullName}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            error={formik.touched.fullName && Boolean(formik.errors.fullName)}
            helperText={formik.touched.fullName && formik.errors.fullName}
          />
        </Grid>
{/* Email */}
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Email"
            name="email"
            variant="outlined"
            size="large"
            value={formik.values.email}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            error={formik.touched.email && Boolean(formik.errors.email)}
            helperText={formik.touched.email && formik.errors.email}
          />
        </Grid>
{/* Password */}
        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Password"
            name="password"
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                  edge="end"
                >
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
              )
            }}
            variant="outlined"
            size="large"
            type={showPassword ? 'text' : 'password'}
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            error={formik.touched.password && Boolean(formik.errors.password)}
            helperText={formik.touched.password && formik.errors.password}
          />
        </Grid>
{/* Day */}
        <Grid item xs={4}>
          <InputLabel>Date</InputLabel>
          <Select name="day" 
          fullWidth
          onChange={handleDateChange("day")}
          onBlur={formik.handleBlur}
          value={formik.values.dateOfBirth.day}>
            {days.map((day) => (
              <MenuItem key={day} value={day}>
                {day}
              </MenuItem>
            ))}
          </Select>
        </Grid>
{/* Month */}
        <Grid item xs={4}>
          <InputLabel>Month</InputLabel>
          <Select name="month"
          fullWidth 
          onChange={handleDateChange("month")}
          onBlur={formik.handleBlur}
          value={formik.values.dateOfBirth.month}>
            {months.map((month) => (
              <MenuItem key={month.label} value={month.value}>
                {month.label}
              </MenuItem>
            ))}
          </Select>
        </Grid>
{/* Year */}
        <Grid item xs={4}>
          <InputLabel>Year</InputLabel>
          <Select name="year" 
          fullWidth
          onChange={handleDateChange("year")}
          onBlur={formik.handleBlur}
          value={formik.values.dateOfBirth.year}>
            {years.map((year) => (
              <MenuItem key={year} value={year}>
                {year}
              </MenuItem>
            ))}
          </Select>
        </Grid>
{/* Submit */}
        <Grid className="mt-20" item xs={12}>
          <Button
            sx={{ borderRadius: "29px", py: "15px", bgcolor: "blue[500]" }}
            type="submit"
            fullWidth
            variant="contained"
            size="large"
          >
            signup
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

export default SignupForm;
